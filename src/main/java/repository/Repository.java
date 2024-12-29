package repository;

import javabean.Expense;

import java.util.ArrayList;

public interface Repository {
    boolean addExpense(Expense expense);
    Expense updateExpense(Expense expense);
    boolean deleteExpense(Expense expense);
    Expense getExpenseById(int id);
    ArrayList<Expense> getAllExpenses();
}
