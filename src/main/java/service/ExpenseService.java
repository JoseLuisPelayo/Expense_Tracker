package service;

import javabean.Expense;
import repository.ExpenseRepository;
import repository.Repository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides services for managing Expense objects.
 * It interacts to the ExpenseRepository to perform CRUD operations and other business logic.
 *
 *  * @author <p>Jose Luis García Pelayo</p>
 *  * @version 1.0.1
 *  * @see MockJsonManager
 */
public class ExpenseService {
    Repository repo;

    /**
     * Constructs an ExpenseService with the specified repository
     *
     * @param repo the repository to manage expense data
     * @throws IOException if an I/O error occurs while initializing the repository
     */
    public ExpenseService(ExpenseRepository repo) throws IOException {
        this.repo = repo;
    }

    /**
     * Adds a new expense to the repository if the expense is valid.
     *
     * @param expense the Expense object to be added
     * @return true if the expense was successfully added, false otherwise
     */
    public boolean addExpense(Expense expense) {
        if (expenseIsValid(expense)) return repo.addExpense(expense);

        return false;
    }

    /**
     * Updates an expense to the repository if it is valid.
     *
     * @param expense the Expense object with updated information
     * @return the updated expense object if successful, null otherwise
     */
    public Expense updateExpense(Expense expense) {
        if (expenseIsValid(expense) && repo.getAllExpenses().contains(expense))
            return repo.updateExpense(expense);

        return null;

    }

    /**
     * Delete an expense from the repository by its id.
     *
     * @param id the id of the expense to be deleted
     * @return true if the expense was successfully deleted, false otherwise
     */
    public boolean deleteExpense(int id) {
        Expense expense = repo.getExpenseById(id);

        if (expense != null) return repo.deleteExpense(expense);

        return false;
    }

    /**
     * Retrieves an expense from the repository by its id.
     *
     * @param id the id of the expense to be retrieved
     * @return the Expense object with the specified id , or null if no such expense exists
     */
    public Expense getExpenseById(int id) {
        return repo.getExpenseById(id);
    }

    /**
     * Retrieves all the expenses from the repository.
     *
     * @return an ArrayList of all Expense objects in the repository
     */
    public ArrayList<Expense> getAllExpenses() {
        return repo.getAllExpenses();
    }

    /**
     * Validates an expense object.
     * This method checks whether the given expense object is valid. An expense is considered valid if:
     * - It is not null.
     * - The description is not null and not empty.
     * - The amount is greater than 0.
     * - The category is not null.
     *
     * @param expense The expense object to be validated.
     * @return {@code true} if the expense is valid; {@code false} otherwise.
     */
    private boolean expenseIsValid(Expense expense) {
        if (
                expense == null
                || expense.getDescription() == null
                || expense.getDescription().isEmpty()
                || expense.getAmount() <= 0
                || expense.getCategory() == null
        )
            return false;

        return true;
    }

    /**
     * Calculates the total amount of all expenses of the current year.
     *
     * @return the sum of all expenses
     */
    public double getExpensesSummary() {
        ArrayList<Expense> expenses = getAllExpenses();
        double sum = 0;

        for (Expense expense : expenses) {
            if (expense.getDate().getYear() == LocalDate.now().getYear())
                sum += expense.getAmount();
        }

        return sum;
    }

    /**
     * Calculates the total amount of all expenses for a specific month of the current year.
     *
     * @param month the number of month for which to calculate the expense summary
     * @return the sum of all expenses for the specified month
     */
    public double getExpensesSummary(int month) {
        ArrayList<Expense> expenses = getAllExpenses();
        double sum = 0;

        for (Expense expense : expenses) {
            if (
                expense.getDate().getMonthValue() == month &&
                expense.getDate().getYear() == LocalDate.now().getYear()
                )
                sum += expense.getAmount();
        }

        return sum;
    }

    /**
     * Calculates the total amount of expenses for a specific category.
     * This method filters the list of all expenses to include only those in the specified category,
     * then calculates the sum of their amounts.
     *
     * @param category The category for which to calculate the total expenses.
     * @return The total amount of expenses for the specified category.
     */
    public double getExpensesSummary(Expense.Category category) {
        double sum = 0;
        List<Expense> expenses = getAllExpenses().stream()
                .filter(expense -> expense.getCategory().equals(category))
                .toList();

        for (Expense expense : expenses) {
            sum += expense.getAmount();
        }

        return sum;
    }

    /**
     * Calculates the total amount of all expenses for a specific year
     *
     * @param year the number of year for which to calculate the expense summary
     * @return the sum of all expenses for the specified year
     */
    public double getYearExpensesSummary(int year) {
        ArrayList<Expense> expenses = getAllExpenses();
        double sum = 0;

        for (Expense expense : expenses) {
            if (expense.getDate().getYear() == year)
                sum += expense.getAmount();
        }

        return sum;
    }

    /**
     * Retrieves all expenses for a specific month.
     *
     * @param month The month for which to retrieve expenses (1-12).
     * @return A list of expenses for the specified month.
     */
    public ArrayList<Expense> getExpensesByMonth(int month) {
        ArrayList<Expense> expensesByMonth = new ArrayList<>();

        getAllExpenses().stream().
                filter(expense -> expense.getDate().getMonthValue() == month).
                forEach(expense -> expensesByMonth.add(expense));

        return expensesByMonth;
    }

    /**
     * Retrieves all expenses for a specific year.
     *
     * @param year The year for which to retrieve expenses.
     * @return A list of expenses for the specified year.
     */
    public ArrayList<Expense> getExpensesByYear(int year) {
        ArrayList<Expense> expensesByYear = new ArrayList<>();

        getAllExpenses().stream().
                filter(expense -> expense.getDate().getYear() == year).
                forEach(expense -> expensesByYear.add(expense));

        return expensesByYear;
    }

    /**
     * Retrieves all expenses for a specific category.
     *
     * @param category The category for which to retrieve expenses.
     * @return A list of expenses for the specified category.
     */
    public List<Expense> getExpensesByCategory(Expense.Category category) {
        return getAllExpenses().stream().
                filter(expense -> expense.getCategory().equals(category)).
                toList();
    }
}
