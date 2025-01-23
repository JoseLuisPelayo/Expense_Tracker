package service;

import javabean.Expense;
import org.junit.Assert;
import org.junit.Test;
import repository.ExpenseRepository;
import utils.MockJsonManager;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Unit tests for the ExpenseService class
 *
 * @author <p>Jose Luis García Pelayo</p>
 * @version 1.0.1
 * @see MockJsonManager
 */

public class ExpenseServiceTest {
    private final ExpenseService serv = new ExpenseService(new ExpenseRepository(new MockJsonManager()));

    /**
     * Constructs an ExpenseServiceTest and initializes the ExpenseService.
     *
     * @throws IOException if an I/O error occurs while initializing the ExpenseService
     */
    public ExpenseServiceTest() throws IOException {
    }

    /**
     * Tests the addExpense method with a valid expense.
     * It verifies that the expense is added successfully and the list size increases by one.
     */
    @Test
    public void addExpense() {
        //Arrange
        int listSize = serv.getAllExpenses().size();
        Expense expense = new Expense();

        expense.setId(serv.getAllExpenses().getLast().getId() + 1);
        expense.setDescription("Unit Test");
        expense.setAmount(2.35);
        expense.setDate(LocalDate.now());
        expense.setCategory(Expense.Category.Health);

        //Act
        boolean resp = serv.addExpense(expense);

        //Assert
        assertEquals(listSize + 1, serv.getAllExpenses().size());
        assertEquals(expense, serv.getAllExpenses().getLast());
        assertTrue(resp);
    }

    /**
     * Tests the addExpense method with an expense that has an empty description.
     * It verifies that the expense is not added and the list size remains the same.
     */
    @Test
    public void addExpenseWithVoidDescription() {
        //Arrange
        int listSize = serv.getAllExpenses().size();
        Expense expense = new Expense();

        expense.setId(serv.getAllExpenses().getLast().getId() + 1);
        expense.setDescription("");
        expense.setAmount(2.35);
        expense.setDate(LocalDate.now());
        expense.setCategory(Expense.Category.Health);

        //Act
        boolean resp = serv.addExpense(expense);

        //Assert
        assertEquals(listSize, serv.getAllExpenses().size());
        assertFalse(resp);
    }

    /**
     * Tests the addExpense method with expenses that have null description.
     * It verifies that the expenses are not added and the list size remains the same.
     */
    @Test
    public void addExpenseWithNullDescription() {
        //Arrange
        int listSize = serv.getAllExpenses().size();
        Expense expense = new Expense();

        expense.setDescription(null);
        expense.setId(serv.getAllExpenses().getLast().getId() + 1);
        expense.setAmount(2.35);
        expense.setDate(LocalDate.now());
        expense.setCategory(Expense.Category.Health);

        //Act
        boolean resp = serv.addExpense(expense);

        //Assert
        assertEquals(listSize, serv.getAllExpenses().size());
        assertFalse(resp);
    }

    /**
     * Tests the addExpense method with expenses that have negative or zero amount.
     * It verifies that the expenses are not added and the list size remains the same.
     */
    @Test
    public void addExpenseWithNegativeAmountAnd0Amount() {
        //Arrange
        int listSize = serv.getAllExpenses().size();
        Expense expense = new Expense();
        Expense expense2 = new Expense();


        expense.setId(serv.getAllExpenses().getLast().getId() + 1);
        expense.setDescription("Unit Test");
        expense.setAmount(0);
        expense.setDate(LocalDate.now());
        expense.setCategory(Expense.Category.Health);

        expense2.setId(serv.getAllExpenses().getLast().getId() + 1);
        expense2.setDescription("Unit Test2");
        expense2.setAmount(-10);
        expense2.setDate(LocalDate.now());
        expense2.setCategory(Expense.Category.Others);

        //Act
        boolean resp = serv.addExpense(expense);
        boolean resp2 = serv.addExpense(expense2);

        //Assert
        assertEquals(listSize, serv.getAllExpenses().size());
        assertFalse(resp);
        assertFalse(resp2);
    }

    /**
     * Tests the addExpense method with a less than 1900 year date.
     * It verifies that the expense is not added and the method returns false.
     */
    @Test
    public void addExpenseWithLessThan1900Year() {
        //Arrange
        int listSize = serv.getAllExpenses().size();
        Expense expense = new Expense();

        expense.setId(serv.getAllExpenses().getLast().getId() + 1);
        expense.setDescription("Unit Test");
        expense.setAmount(0);
        expense.setDate(LocalDate.of(1899, 1, 1));
        expense.setCategory(Expense.Category.Health);

        //Act
        boolean resp = serv.addExpense(expense);

        //Assert
        assertEquals(listSize, serv.getAllExpenses().size());
        assertFalse(resp);
    }

    /**
     * Tests the addExpense method with a year 0 date.
     * It verifies that the expense is not added and the method returns false.
     */
    @Test
    public void addExpenseWithYear0() {
        //Arrange
        int listSize = serv.getAllExpenses().size();
        Expense expense = new Expense();

        expense.setId(serv.getAllExpenses().getLast().getId() + 1);
        expense.setDescription("Unit Test");
        expense.setAmount(0);
        expense.setDate(LocalDate.of(0, 1, 1));
        expense.setCategory(Expense.Category.Health);

        //Act
        boolean resp = serv.addExpense(expense);

        //Assert
        assertEquals(listSize, serv.getAllExpenses().size());
        assertFalse(resp);
    }

    /**
     * Tests the addExpense method with a negative year date.
     * It verifies that the expense is not added and the method returns false.
     */
    @Test
    public void addExpenseWithNegativeYear() {
        //Arrange
        int listSize = serv.getAllExpenses().size();
        Expense expense = new Expense();

        expense.setId(serv.getAllExpenses().getLast().getId() + 1);
        expense.setDescription("Unit Test");
        expense.setAmount(0);
        expense.setDate(LocalDate.of(-120, 1, 1));
        expense.setCategory(Expense.Category.Health);

        //Act
        boolean resp = serv.addExpense(expense);

        //Assert
        assertEquals(listSize, serv.getAllExpenses().size());
        assertFalse(resp);
    }

    /**
     * Tests the addExpense method with a null expense.
     * It verifies that the expenses are not added and the list size remains the same.
     */
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

    /**
     * Tests the updateExpense method with valid data.
     * It verifies that the expense is updated successfully.
     */
    @Test
    public void updateExpense() {
        //Arrange
        Expense expense = serv.getAllExpenses().getFirst();

        expense.setDescription("New Description");
        expense.setAmount(2.99);
        expense.setDate(LocalDate.of(2020, 1, 1));
        expense.selectCategory("Health");

        //Act
        Expense modifiedExpense = serv.updateExpense(expense);

        //Assert
        assertEquals("New Description", modifiedExpense.getDescription());
        assertEquals(2.99, modifiedExpense.getAmount(), 0);
        assertEquals(LocalDate.of(2020, 1, 1), modifiedExpense.getDate());
        assertEquals(Expense.Category.Health, modifiedExpense.getCategory());
    }

    /**
     * Tests the updateExpense method with expense that have an empty description;
     * It verifies that the returned expense is null and that the attempted expense data has not been changed;
     */
    @Test
    public void updateExpenseWithEmptyDescription() {
        //Arrange
        Expense expense = new Expense();
        expense.setId(serv.getAllExpenses().getFirst().getId());
        expense.setDescription(serv.getAllExpenses().getFirst().getDescription());
        expense.setAmount(2.99);
        expense.setDate(LocalDate.of(2020, 1, 1));

        String description = expense.getDescription();
        expense.setDescription("");

        //Act
        Expense modifiedExpense = serv.updateExpense(expense);

        //Assert
        assertNull(modifiedExpense);
        assertEquals(description, serv.getAllExpenses().getFirst().getDescription());
    }

    /**
     * Tests the updateExpense method with expense that have zero amount;
     * It verifies that the returned expense is null and that the attempted expense data has not been changed;
     */
    @Test
    public void updateExpenseWithAmountZero() {
        //Arrange
        Expense expense = new Expense();
        expense.setId(serv.getAllExpenses().getFirst().getId());
        expense.setDescription(serv.getAllExpenses().getFirst().getDescription());
        expense.setAmount(serv.getAllExpenses().getFirst().getAmount());
        expense.setDate(serv.getAllExpenses().getFirst().getDate());

        double amount = expense.getAmount();
        expense.setAmount(0);


        //Act
        Expense modifiedExpense = serv.updateExpense(expense);

        //Assert
        assertNull(modifiedExpense);
        assertEquals(amount, serv.getAllExpenses().getFirst().getAmount(), 0);
    }

    /**
     * Tests the updateExpense method with expense that have negative amount;
     * It verifies that the returned expense is null and that the attempted expense data has not been changed;
     */
    @Test
    public void updateExpenseWithNegativeAmount() {
        //Arrange
        Expense expense = new Expense();
        expense.setId(serv.getAllExpenses().getFirst().getId());
        expense.setDescription(serv.getAllExpenses().getFirst().getDescription());
        expense.setAmount(serv.getAllExpenses().getFirst().getAmount());
        expense.setDate(serv.getAllExpenses().getFirst().getDate());

        double amount = expense.getAmount();
        expense.setAmount(-100);

        //Act
        Expense modifiedExpense = serv.updateExpense(expense);


        //Assert
        assertNull(modifiedExpense);
        assertEquals(amount, serv.getAllExpenses().getFirst().getAmount(), 0);
    }

    /**
     * Tests the updateExpense with expense that have null category;
     * It verifies that the returned expense is null and that the attempted expense data has not been changed;
     */
    @Test
    public void updateExpenseWithNullCategory() {
        //Arrange
        Expense expense = new Expense();
        expense.setId(serv.getAllExpenses().getFirst().getId());
        expense.setDescription(serv.getAllExpenses().getFirst().getDescription());
        expense.setAmount(serv.getAllExpenses().getFirst().getAmount());
        expense.setDate(serv.getAllExpenses().getFirst().getDate());
        expense.setCategory(serv.getAllExpenses().getFirst().getCategory());

        Expense.Category category = expense.getCategory();
        expense.setCategory(null);

        //Act
        Expense modifiedExpense = serv.updateExpense(expense);

        //Assert
        assertNull(modifiedExpense);
        assertEquals(category, serv.getAllExpenses().getFirst().getCategory());
    }

    /**
     * Tests the updateExpense method with a non-existent expense;
     * It verifies that the returned expense is null
     */
    @Test
    public void updateNonExistentExpense() {
        //Arrange
        Expense expense = new Expense();

        expense.setId(99);
        expense.setDescription("Unit Test");
        expense.setAmount(2.99);
        expense.setDate(LocalDate.now());
        expense.setCategory(Expense.Category.Health);

        //Act
        Expense resp = serv.updateExpense(expense);

        //Assert
        assertNull(resp);
    }

    /**
     * Tests the addExpense method with a less than 1900 year date.
     * It verifies that the expense is not added and the method returns false.
     */
    @Test
    public void updateExpenseWithLessThan1900Year() {
        //Arrange
        Expense expense = new Expense();

        expense.setId(serv.getAllExpenses().getLast().getId() + 1);
        expense.setDescription("Unit Test");
        expense.setAmount(0);
        expense.setDate(LocalDate.of(1899, 1, 1));
        expense.setCategory(Expense.Category.Health);

        //Act
        Expense resp = serv.updateExpense(expense);

        //Assert
        assertNull(resp);
    }

    /**
     * Tests the addExpense method with a year 0 date.
     * It verifies that the expense is not added and the method returns false.
     */
    @Test
    public void updateExpenseWithYear0() {
        //Arrange
        Expense expense = new Expense();

        expense.setId(serv.getAllExpenses().getLast().getId() + 1);
        expense.setDescription("Unit Test");
        expense.setAmount(0);
        expense.setDate(LocalDate.of(0, 1, 1));
        expense.setCategory(Expense.Category.Health);

        //Act
        Expense resp = serv.updateExpense(expense);

        //Assert
        assertNull(resp);
    }

    /**
     * Tests the addExpense method with a negative year date.
     * It verifies that the expense is not added and the method returns false.
     */
    @Test
    public void updateExpenseWithNegativeYear() {
        //Arrange
        Expense expense = new Expense();

        expense.setId(serv.getAllExpenses().getLast().getId() + 1);
        expense.setDescription("Unit Test");
        expense.setAmount(0);
        expense.setDate(LocalDate.of(-120, 1, 1));
        expense.setCategory(Expense.Category.Health);

        //Act
        Expense resp = serv.updateExpense(expense);

        //Assert
        assertNull(resp);
    }


    /**
     * Tests the deleteExpense method with an existing expense.
     * It verifies that the expense is deleted successfully, the list size decreases by one,
     * and the expense is no longer present in the list.
     */
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

    /**
     * Tests the deleteExpense method with a non-existent expense ID.
     * It verifies that the deletion fails and the list size remains the same.
     */
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

    /**
     * Tests the getExpenseById method with an existing expense ID.
     * It verifies that the returned expense matches the expected expense.
     */
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

    /**
     * Tests the getExpenseById method with a non-existent expense ID.
     * It verifies that the method returns null.
     */
    @Test
    public void getNonExistentExpenseById() {
        //Arrange
        int index = 0;

        //Act
        Expense resp = serv.getExpenseById(index);

        //Assert
        assertNull(resp);
    }

    /**
     * Tests the getExpensesSummary method.
     * It verifies that the summary of expenses is calculated correctly.
     */
    @Test
    public void getExpensesSummary() {
        //Arrange
        ArrayList<Expense> expenses = serv.getAllExpenses();
        expenses.removeAll(serv.getAllExpenses());
        assertEquals(0, expenses.size());

        Expense expense = new Expense();
        Expense expense2 = new Expense();

        expense.setId(1);
        expense.setDescription("Unit Test");
        expense.setAmount(2.50);
        expense.setDate(LocalDate.of(LocalDate.now().getYear(),1,1));
        expense.setCategory(Expense.Category.Health);

        expense2.setId(2);
        expense2.setDescription("Unit Test2");
        expense2.setAmount(2.50);
        expense2.setDate(LocalDate.of(LocalDate.now().getYear(),2,1));
        expense2.setCategory(Expense.Category.Others);

        expenses.add(expense);
        expenses.add(expense2);

        //Act
        double sum = serv.getExpensesSummary();

        //Assert
        assertEquals(5.00, sum, 0.001);
    }

    /**
     * Tests the getExpensesSummaryByMonth method.
     * It verifies that the summary of expenses for a specific month is calculated correctly.
     */
    @Test
    public void getExpensesSummaryByMonth() {
        //Arrange
        ArrayList<Expense> expenses = serv.getAllExpenses();
        expenses.removeAll(serv.getAllExpenses());

        Expense expense = new Expense();
        Expense expense2 = new Expense();

        expense.setId(1);
        expense.setDescription("Unit Test");
        expense.setAmount(2.50);
        expense.setDate(LocalDate.of(2024,1,1));
        expense.setCategory(Expense.Category.Health);

        expense2.setId(2);
        expense2.setDescription("Unit Test2");
        expense2.setAmount(2.50);
        expense2.setDate(LocalDate.of(LocalDate.now().getYear(),2,1));
        expense2.setCategory(Expense.Category.Others);

        expenses.add(expense);
        expenses.add(expense2);

        //Act
        double sum = serv.getExpensesSummary(2);

        //Assert
        assertEquals(2.50, sum, 0);
    }

    /**
     * Tests the getExpensesSummary method with an empty list of expenses.
     * It verifies that the summary of expenses is zero.
     */
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

    /**
     * Tests the getExpensesSummary method for a specific category.
     * It verifies that the summary of expenses for the given category is calculated correctly.
     */
    @Test
    public void getExpensesSummaryByCategory() {
        //Arrange
        ArrayList<Expense> expenses = serv.getAllExpenses();
        expenses.removeAll(serv.getAllExpenses());

        Expense expense = new Expense();
        Expense expense2 = new Expense();

        expense.setId(1);
        expense.setDescription("Unit Test");
        expense.setAmount(2.50);
        expense.setDate(LocalDate.of(2024,1,1));
        expense.setCategory(Expense.Category.Health);

        expense2.setId(2);
        expense2.setDescription("Unit Test2");
        expense2.setAmount(2.50);
        expense2.setDate(LocalDate.of(LocalDate.now().getYear(),2,1));
        expense2.setCategory(Expense.Category.Others);

        expenses.add(expense);
        expenses.add(expense2);

        //Act
        double res = serv.getExpensesSummary(Expense.Category.Health);

        //Assert
        assertEquals(2.50, res, 0);
    }

    /**
     * Tests the getExpensesSummary method with a null category.
     * It verifies that the summary of expenses for the null category is zero.
     */
    @Test
    public void getExpensesSummaryByCategoryWithNullCategory() {
        //Arrange
        ArrayList<Expense> expenses = serv.getAllExpenses();

        //Act
        double res = serv.getExpensesSummary(null);

        //Assert
        assertEquals(0, res, 0);
    }

    /**
     * Tests the getYearExpensesSummary method for a specific year.
     * It verifies that the summary of expenses for the given year is calculated correctly.
     */
    @Test
    public void getExpensesSummaryByYear() {
        //Arrange
        ArrayList<Expense> expenses = serv.getAllExpenses();

        expenses.removeAll(serv.getAllExpenses());

        Expense expense = new Expense();
        Expense expense2 = new Expense();

        expense.setId(1);
        expense.setDescription("Unit Test");
        expense.setAmount(2.50);
        expense.setDate(LocalDate.of(2024,1,1));
        expense.setCategory(Expense.Category.Health);

        expense2.setId(2);
        expense2.setDescription("Unit Test2");
        expense2.setAmount(2.50);
        expense2.setDate(LocalDate.of(2025,2,1));
        expense2.setCategory(Expense.Category.Others);

        expenses.add(expense);
        expenses.add(expense2);

        //Act
        double res = serv.getYearExpensesSummary(2024);

        //Assert
        assertEquals(2.50, res, 0);
    }

    /**
     * Tests the getYearExpensesSummary method for the year 0.
     * It verifies that the summary of expenses for the year 0 is 0.
     */
    @Test
    public void getExpensesSummaryByYearWith0Year() {
        //Arrange
        ArrayList<Expense> expenses = serv.getAllExpenses();

        expenses.removeAll(serv.getAllExpenses());

        Expense expense = new Expense();
        Expense expense2 = new Expense();

        expense.setId(1);
        expense.setDescription("Unit Test");
        expense.setAmount(2.50);
        expense.setDate(LocalDate.of(2024,1,1));
        expense.setCategory(Expense.Category.Health);

        expense2.setId(2);
        expense2.setDescription("Unit Test2");
        expense2.setAmount(2.50);
        expense2.setDate(LocalDate.of(2025,2,1));
        expense2.setCategory(Expense.Category.Others);

        expenses.add(expense);
        expenses.add(expense2);

        //Act
        double res = serv.getYearExpensesSummary(0);

        //Assert
        assertEquals(0, res, 0);
    }

    /**
     * Tests the getYearExpensesSummary method for a negative year.
     * It verifies that the summary of expenses for a negative year is 0).
     */
    @Test
    public void getExpensesSummaryByYearWithNegativeYear() {
        //Arrange
        ArrayList<Expense> expenses = serv.getAllExpenses();

        expenses.removeAll(serv.getAllExpenses());

        Expense expense = new Expense();
        Expense expense2 = new Expense();

        expense.setId(1);
        expense.setDescription("Unit Test");
        expense.setAmount(2.50);
        expense.setDate(LocalDate.of(2024,1,1));
        expense.setCategory(Expense.Category.Health);

        expense2.setId(2);
        expense2.setDescription("Unit Test2");
        expense2.setAmount(2.50);
        expense2.setDate(LocalDate.of(2025,2,1));
        expense2.setCategory(Expense.Category.Others);

        expenses.add(expense);
        expenses.add(expense2);

        //Act
        double res = serv.getYearExpensesSummary(-125);

        //Assert
        assertEquals(0, res, 0);
    }


}
