package javabean;

import java.time.LocalDate;
import java.util.Objects;

public class Expense {
    private int id;
    private String description;
    private double amount;
    private LocalDate date;

    public Expense() {}

    public Expense(int id, String description, double amount, LocalDate date) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Expense expense)) return false;
        return id == expense.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return String.format("""
                "id":           %d,
                "description":  "%s",
                "amount":       %.2f,
                "date":         "%s"
                """, id, description, amount, date);
    }
}


