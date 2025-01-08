package javabean;

import java.time.LocalDate;
import java.util.Objects;

/**
 * This class represents an Expense with an id, description, amount, and date.
 * It provides getters and setters for each field, and overrides the equals, hashCode, and toString methods.
 *
 * <p>Author: Jose Pelayo</p>
 */
public class Expense {
    private int id;
    private String description;
    private double amount;
    private LocalDate date;

    public Expense() {}

    /**
     * Creates an expense with the specified id, description, amount and date
     * @param id the expense id
     * @param description the expense description
     * @param amount the expense amount
     * @param date the expense date
     */
    public Expense(int id, String description, double amount, LocalDate date) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    /**
     *
     * @return return the expense id
     */
    public int getId() {
        return id;
    }

    /**
     * set the expense id
     * @param id the expense id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return the expense description
     */
    public String getDescription() {
        return description;
    }

    /**
     * set the expense description
     * @param description the expense description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return the expense amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * set the expense amount
     * @param amount the expense amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     *
     * @return the expense date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * set the expense date
     * @param date the expense date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Compares this expense to the specified object. The result is true
     * if and only if the argument is not null and is an Expense object
     * that has the same id as this object.
     *
     * @param o the object to compare the expense
     * @return true if the given object represents an expense with the same id, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Expense expense)) return false;
        return id == expense.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    /**
     *
     *  @return the string with expense info
     */
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


