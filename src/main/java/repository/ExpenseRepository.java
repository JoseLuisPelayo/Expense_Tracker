package repository;

import javabean.Expense;
import utils.JsonManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ExpenseRepository implements Repository {
    ArrayList<Expense> expenses;

    public ExpenseRepository() throws IOException {
        this.expenses = (ArrayList<Expense>) JsonManager.jsonDataToArrayList(new File("expenseJson.json"), Expense.class);
    }

    public boolean addExpense(Expense expense) {
       try {
        if (expenses.add(expense))
            return JsonManager.ListToJson(new File("expenseJson.json"), expenses);

        return false;

       } catch (IOException e) {
        return false;
       }
    }

    @Override
    public Expense updateExpense(Expense expense) {
        try {
       this.expenses.set(expenses.indexOf(expense), expense);

       if (JsonManager.ListToJson(new File("expenseJson.json"), expenses))
            return expenses.get(expenses.indexOf(expense));

        return null;
        } catch (IOException e) {
            return null;
        }

    }

    @Override
    public boolean deleteExpense(Expense expense) {
        try {
        if (this.expenses.remove(expense))
            return JsonManager.ListToJson(new File("expenseJson.json"), expenses);

        return false;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public Expense getExpenseById(int id) {
        return this.expenses.stream().filter(expense -> expense.getId() == id)
                                        .findFirst()
                                        .orElse(null);
    }

    @Override
    public ArrayList<Expense> getAllExpenses() {
        return this.expenses;
    }
}
