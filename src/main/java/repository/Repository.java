package repository;

import javabean.Expense;

import java.util.ArrayList;

public interface Repository {
    Expense addExpense(Expense expense);
    Expense updateExpense(Expense expense);
    void deleteExpense(Expense expense);
    Expense getExpenseById(int id);
    ArrayList<Expense> getAllExpenses();
}