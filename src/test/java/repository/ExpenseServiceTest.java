package repository;

import javabean.Expense;
import org.junit.Test;
import service.ExpenseService;
import utils.MockJsonManager;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ExpenseServiceTest {
    private final ExpenseService serv = new ExpenseService(new ExpenseRepository(new MockJsonManager()));

    public ExpenseServiceTest() throws IOException {
    }

    @Test
    public void addExpense() {
        //Arrange
        int listSize = serv.getAllExpenses().size();
        Expense expense = new Expense(
                                    serv.getAllExpenses().getLast().getId() +1,
                                    "Unit Test",
                                    2.35,
                                    LocalDate.now()
                                    );
        //Act
        boolean resp = serv.addExpense(expense);

        //Assert
        assertEquals(listSize + 1, serv.getAllExpenses().size());
        assertEquals(expense, serv.getAllExpenses().getLast());
        assertTrue(resp);
    }

    @Test
    public void addExpenseWithVoidDescription() {
        //Arrange
        Expense expense = new Expense(4,"", 22.55, LocalDate.now());

        //Act
        boolean resp = serv.addExpense(expense);

        //Assert
        assertFalse(resp);
    }

    @Test
    public void addExpenseWithNegativeAmount() {
        //Arrange
        Expense expense = new Expense(4,"", 0, LocalDate.now());
        Expense expense2 = new Expense(4,"", -10, LocalDate.now());

        //Act
        boolean resp = serv.addExpense(expense);
        boolean resp2 = serv.addExpense(expense2);

        //Assert
        assertFalse(resp);
        assertFalse(resp2);
    }

    @Test
    public void addNullExpense() {
        //Arrange
        int listSize = serv.getAllExpenses().size();

        //Act
        boolean resp = serv.addExpense(null);

        //Assert
        assertEquals(listSize, serv.getAllExpenses().size());
        assertFalse(resp);
    }

    @Test
    public void updateExpense() {
        //Arrange
        Expense expense = serv.getAllExpenses().getFirst();

        expense.setDescription("New Description");
        expense.setAmount(2.99);
        expense.setDate(LocalDate.of(2020, 1, 1));

        //Act
        Expense modifiedExpense = serv.updateExpense(expense);

        //Assert
        assertEquals("New Description", modifiedExpense.getDescription());
        assertEquals(2.99, modifiedExpense.getAmount(), 0);
        assertEquals(LocalDate.of(2020, 1, 1), modifiedExpense.getDate());
    }

    @Test
    public void updateNonExistentExpense() {
        //Arrange
        Expense expense = new Expense(99,"Unit Test",2.99,LocalDate.now());

        //Act
        Expense resp = serv.updateExpense(expense);

        //Assert
        assertNull(resp);
    }

    @Test
    public void deleteExpense() {
        //Arrange
        Expense expense = serv.getAllExpenses().getFirst();
        int expenseId = expense.getId();
        int listSize = serv.getAllExpenses().size();

        //Act
        boolean resp = serv.deleteExpense(expenseId);

        //Assert
        assertTrue(resp);
        assertEquals(listSize-1, serv.getAllExpenses().size());
        assertFalse(serv.getAllExpenses().contains(expense));

    }

    @Test
    public void deleteNonExistentExpense() {
        //Arrange
        int listSize = serv.getAllExpenses().size();

        //Act
        boolean resp = serv.deleteExpense(0);

        //Assert
        assertFalse(resp);
        assertEquals(listSize, serv.getAllExpenses().size());
    }

    @Test
    public void getExpenseById() {
        //Arrange
        Expense expense = serv.getAllExpenses().getFirst();
        int id = expense.getId();

        //Act
        Expense expense2 = serv.getExpenseById(id);

        //Assert
        assertEquals(expense, expense2);
    }

    @Test
    public void getNonExistentExpenseById() {
        //Arrange
        int index = 0;

        //Act
        Expense resp = serv.getExpenseById(index);

        //Assert
        assertNull(resp);
    }

    @Test
    public void getExpensesSummary() {
        //Arrange
        ArrayList<Expense> expenses = serv.getAllExpenses();
        expenses.removeAll(serv.getAllExpenses());
        assertEquals(0, expenses.size());

        expenses.add( new Expense(1, "Unit Test", 2.50, LocalDate.of(2024, 1, 1)));
        expenses.add(new Expense(2, "Unit Test2", 2.50, LocalDate.of(2024, 2, 1)));

        //Act
        double sum = serv.getExpensesSummary();

        //Assert
        assertEquals(5.00, sum, 0.001);
    }

    @Test
    public void getExpensesSummaryByMonth() {
        //Arrange
        ArrayList<Expense> expenses = serv.getAllExpenses();
        expenses.removeAll(serv.getAllExpenses());
        assertEquals(0, expenses.size());

        expenses.add( new Expense(1, "Unit Test", 2.50, LocalDate.of(2024, 1, 1)));
        expenses.add(new Expense(2, "Unit Test2", 2.50, LocalDate.of(LocalDate.now().getYear(), 2, 1)));

        //Act
        double sum = serv.getExpensesSummary(2);

        //Assert
        assertEquals(2.50, sum, 0);
    }

    @Test
    public void getEmptyExpensesListSummary() {
        //Arrange
        ArrayList<Expense> expenses = serv.getAllExpenses();
        expenses.removeAll(serv.getAllExpenses());

        //Act
        double sum = serv.getExpensesSummary();

        //Assert
        assertEquals(0, expenses.size());
        assertEquals(0, sum, 0);
    }
    

}
