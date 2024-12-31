package repository;

import javabean.Expense;
import org.junit.Test;
import utils.MockJsonManager;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
        Expense expense = repo.getAllExpenses().getLast();

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

    @Test public void getExpenseById() {
        //Arrange
        Expense expense = repo.getAllExpenses().getFirst();
        int id = expense.getId();

        //Act
        Expense expense2 = repo.getExpenseById(id);

        //Assert
        assertEquals(expense, expense2);
    }

}
