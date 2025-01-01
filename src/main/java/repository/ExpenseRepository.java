package repository;

import javabean.Expense;
import utils.IJsonManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ExpenseRepository implements Repository {
    ArrayList<Expense> expenses;
    IJsonManager jsonManager;

    public ExpenseRepository(IJsonManager jsonManager) throws IOException {
        this.jsonManager = jsonManager;
        this.expenses = (ArrayList<Expense>) jsonManager.jsonDataToArrayList(new File("expenseJson.json"), Expense.class);
    }

    public boolean addExpense(Expense expense) {
       try {
        if (expenses.add(expense))
            return jsonManager.listToJson(new File("expenseJson.json"), expenses);

        return false;

       } catch (IOException e) {
        return false;
       }
    }

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
