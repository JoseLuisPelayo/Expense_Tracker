package service;

import javabean.Expense;
import org.junit.Assert;
import org.junit.Test;
import repository.ExpenseRepository;
import utils.MockJsonManager;

import java.io.IOException;
import java.time.LocalDate;

public class ExpenseRepositoryTest {
    private final ExpenseRepository repo = new ExpenseRepository(new MockJsonManager());

    public ExpenseRepositoryTest() throws IOException {
    }

    @Test
    public void addExpense() throws IOException {

        int listSize = repo.getAllExpenses().size();

        Expense expense = new Expense(
                                    repo.getAllExpenses().getLast().getId() +1,
                                    "Unit Test",
                                    2.35,
                                    LocalDate.now()
                                    );

        repo.addExpense(expense);

        Assert.assertEquals(listSize+1, repo.getAllExpenses().size());
        Assert.assertEquals(expense, repo.getAllExpenses().getLast());
    }

    @Test
    public

}
