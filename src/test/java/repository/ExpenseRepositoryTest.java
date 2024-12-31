package repository;

import javabean.Expense;
import org.junit.Test;
import utils.MockJsonManager;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class ExpenseRepositoryTest {
    private final ExpenseRepository repo = new ExpenseRepository(new MockJsonManager());

    public ExpenseRepositoryTest() throws IOException {
    }

    @Test
    public void addExpense() {
        //Arrange
        int listSize = repo.getAllExpenses().size();
        Expense expense = new Expense(
                                    repo.getAllExpenses().getLast().getId() +1,
                                    "Unit Test",
                                    2.35,
                                    LocalDate.now()
                                    );
        //Act
        repo.addExpense(expense);

        //Assert
        assertEquals(listSize+1, repo.getAllExpenses().size());
        assertEquals(expense, repo.getAllExpenses().getLast());
    }

    @Test
    public void updateExpense() {
        //Arrange
        Expense expense = repo.getAllExpenses().getFirst();

        expense.setDescription("New Description");
        expense.setAmount(2.99);
        expense.setDate(LocalDate.of(2020, 1, 1));

        //Act
        Expense modifiedExpense = repo.updateExpense(expense);

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
        Expense resp = repo.updateExpense(expense);

        //Assert
        assertNull(resp);
    }

    @Test
    public void deleteExpense() {
        //Arrange
        Expense expense = repo.getAllExpenses().getFirst();
        int listSize = repo.getAllExpenses().size();

        //Act
        repo.deleteExpense(expense);

        //Assert
        assertEquals(listSize-1, repo.getAllExpenses().size());
        assertFalse(repo.getAllExpenses().contains(expense));
    }

    @Test
    public void deleteNonExistentExpense() {
        //Arrange
        int listSize = repo.getAllExpenses().size();
        Expense expense = new Expense(99,"Unit Test",2.99,LocalDate.now());

        //Act
        boolean resp = repo.deleteExpense(expense);

        //Assert
        assertFalse(resp);
        assertEquals(listSize, repo.getAllExpenses().size());
    }

    @Test
    public void getExpenseById() {
        //Arrange
        Expense expense = repo.getAllExpenses().getFirst();
        int id = expense.getId();

        //Act
        Expense expense2 = repo.getExpenseById(id);

        //Assert
        assertEquals(expense, expense2);
    }

    @Test
    public void getNonExistentExpenseById() {
        //Arrange
        int index = 0;

        //Act
        Expense resp = repo.getExpenseById(index);

        //Assert
        assertNull(resp);
    }

}
