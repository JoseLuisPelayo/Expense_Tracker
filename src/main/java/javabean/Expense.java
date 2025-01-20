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
    private Category category;
    public static enum Category{Groceries,Leisure,Electronics,Utilities,Clothing,Health,Others};

    public Expense() {}

    /**
     * Creates an expense with the specified id, description, amount and date
     * @param id the expense id
     * @param description the expense description
     * @param amount the expense amount
     * @param date the expense date
     */
    public Expense(int id, String description, double amount, LocalDate date, Category category) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.category = category;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
                "category"      "%s"
                """, id, description, amount, date, category);
    }

    /**
     * Sets the category of the expense based on the provided category name.
     *
     * @param category the category of the expense to be set.
     * @throws IllegalArgumentException Throws an illegalArgumentException if the param category is null or is empty
     */
    public boolean selectCategory(String category) throws IllegalArgumentException {
        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException("Category cannot be null or blank");
        }

        switch (category.toLowerCase()) {
            case "groceries" -> this.category = Category.Groceries;
            case "leisure" -> this.category = Category.Leisure;
            case "electronics" -> this.category = Category.Electronics;
            case "utilities" -> this.category = Category.Utilities;
            case "clothing" -> this.category = Category.Clothing;
            case "health" -> this.category = Category.Health;
            case "others" -> this.category = Category.Others;
            default -> {
                return false;
            }
        }
        return true;
    }
}


