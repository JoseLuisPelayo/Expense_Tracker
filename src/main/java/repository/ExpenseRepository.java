package repository;

import javabean.Expense;
import utils.IJsonManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class provides an implementation fo the Repository interface for managing Expense objects.
 * It uses a JSON file to store the expense data.
 */
public class ExpenseRepository implements Repository {
    ArrayList<Expense> expenses;
    IJsonManager jsonManager;

    /**
     * Constructs a ExpenseRepository with the specified JSON manager.
     * It initializes the expense list by reading from a JSON file.
     * If the file does not exist, it creates a new one.
     *
     * @param jsonManager the JsonManager to handle JSON operations
     */
    public ExpenseRepository(IJsonManager jsonManager) {
        this.jsonManager = jsonManager;
        this.expenses = (ArrayList<Expense>) jsonManager.jsonDataToArrayList(new File("expenseJson.json"), Expense.class);
    }

    /**
     *Adds a new expense to the repository and updates the JSON file
     *
     * @param expense The Expense object to be added
     * @return true if the expense was successfully added and the JSON file was updated, false otherwise
     */
    @Override
    public boolean addExpense(Expense expense) {
       try {
        if (expenses.add(expense))
            return jsonManager.listToJson(new File("expenseJson.json"), expenses);

        return false;

       } catch (IOException e) {
           return false;
       }
    }

    /**
     * Updates an existent expense in the repository and updates the JSON file.
     *
     * @param expense The expense object with updated info.
     * @return the updated Expense object if successful, null otherwise.
     */
    @Override
    public Expense updateExpense(Expense expense) {
        try {
            this.expenses.set(expenses.indexOf(expense), expense);

            if (jsonManager.listToJson(new File("expenseJson.json"), expenses))
                return expenses.get(expenses.indexOf(expense));

            return null;
        } catch (IOException e) {
            return null;
        }

    }

    /**
     * Delete an existent expense from the repository and updates the JSON file.
     *
     * @param expense The expense object to be deleted
     * @return true if the Expense object was successfully deleted
     */
    @Override
    public boolean deleteExpense(Expense expense) {
        try {
            if (this.expenses.remove(expense))
                return jsonManager.listToJson(new File("expenseJson.json"), expenses);

            return false;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Retrieve an expense from the repository by its id.
     *
     * @param id The Id of the expense to be retrieved
     * @return the expense with the specified id, or null if no such expense exists
     */
    @Override
    public Expense getExpenseById(int id) {
        return this.expenses.stream().filter(expense -> expense.getId() == id)
                                        .findFirst()
                                        .orElse(null);
    }

    /**
     * Retrieve all expenses from the repository.
     *
     * @return an ArrayList of all Expense objects in the repository
     */
    @Override
    public ArrayList<Expense> getAllExpenses() {
        return this.expenses;
    }
}
