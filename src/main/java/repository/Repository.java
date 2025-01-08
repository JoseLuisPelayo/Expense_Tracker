package repository;

import javabean.Expense;

import java.util.ArrayList;

/**
 * This interface defines the contract for a repository to manages Expenses objects.
 * It provides methods to add, update, delete, and retrieve expense.
 */
public interface Repository {
    /**
     * Adds a new expense to the repository
     *
     * @param expense The Expense object to be added
     * @return true if the expense object was successfully added, false otherwise
     */
    boolean addExpense(Expense expense);

    /**
     * Update an existing expense in the repository
     *
     * @param expense The expense object with updated info.
     * @return The updated expense object or null if no such expense exists
     */
    Expense updateExpense(Expense expense);

    /**
     * Delete an existing expense in the repository
     *
     * @param expense The expense object to be deleted
     * @return true if the expense was deleted successfully, false otherwise
     */
    boolean deleteExpense(Expense expense);

    /**
     * Retrieves an expense from the repository by its id
     *
     * @param id The Id of the expense to be retrieved
     * @return the expense object with the specified id, or null if no such expense exists
     */
    Expense getExpenseById(int id);

    /**
     * Retrieves all expenses from the repository
     *
     * @return an ArrayList of all Expense objects in the repository
     */
    ArrayList<Expense> getAllExpenses();
}
