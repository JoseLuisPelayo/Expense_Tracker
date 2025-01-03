package service;

import javabean.Expense;
import repository.ExpenseRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ExpenseService {
    ExpenseRepository repo;

    public ExpenseService(ExpenseRepository repo) throws IOException {
        this.repo = repo;
    }

    public boolean addExpense(Expense expense) {
        if (expense != null)
            if (!expense.getDescription().isEmpty() && expense.getAmount() > 0)
                return repo.addExpense(expense);

        return false;
    }

    public Expense updateExpense(Expense expense) {
        if (
                !repo.getAllExpenses().contains(expense)
                || expense.getDescription().isEmpty()
                || expense.getAmount() <= 0
        )
            return null;

        return repo.updateExpense(expense);
    }

    public boolean deleteExpense(int id) {
        Expense expense = repo.getExpenseById(id);
        if (expense != null) return repo.deleteExpense(expense);
        return false;
    }

    public Expense getExpenseById(int id) {
        return repo.getExpenseById(id);
    }

    public ArrayList<Expense> getAllExpenses() {
        return repo.getAllExpenses();
    }

    public double getExpensesSummary() {
        ArrayList<Expense> expenses = getAllExpenses();
        double sum = 0;

        for (Expense expense : expenses) {
            sum += expense.getAmount();
        }

        return sum;
    }

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
