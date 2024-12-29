package service;

import javabean.Expense;
import repository.ExpenseRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ExpenseService {
    ExpenseRepository repo = new ExpenseRepository();


    public ExpenseService() throws IOException {
    }

    public boolean addExpense(Expense expense) {
        return repo.addExpense(expense);
    }

    public Expense updateExpense(Expense expense) {
        return repo.updateExpense(expense);
    }

    public boolean deleteExpense(int id) {
        return repo.deleteExpense(this.repo.getExpenseById(id));
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
