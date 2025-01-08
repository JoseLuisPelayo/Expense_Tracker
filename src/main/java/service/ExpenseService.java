package service;

import javabean.Expense;
import repository.ExpenseRepository;
import repository.Repository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * This class provides services for managing Expense objects.
 * It interacts to the ExpenseRepository to perform CRUD operations and other business logic.
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
        if (expense != null)
            if (!expense.getDescription().isEmpty() && expense.getAmount() > 0)
                return repo.addExpense(expense);

        return false;
    }

    /**
     * Updates an expense to the repository if it is valid.
     *
     * @param expense the Expense object with updated information
     * @return the updated expense object if successful, null otherwise
     */
    public Expense updateExpense(Expense expense) {
        if (
                !repo.getAllExpenses().contains(expense)
                || expense.getDescription().isEmpty()
                || expense.getAmount() <= 0
        )
            return null;

        return repo.updateExpense(expense);
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
     * Calculates the total amount of all expenses.
     *
     * @return the sum of all expenses
     */
    public double getExpensesSummary() {
        ArrayList<Expense> expenses = getAllExpenses();
        double sum = 0;

        for (Expense expense : expenses) {
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
}
