package utils;

import javabean.Expense;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MockJsonManager implements IJsonManager{
    public <T> List<T> jsonDataToArrayList(File file, Class<T> myClass) {
        ArrayList<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense(1, "Expense test 1", 15.22, LocalDate.now()));
        expenses.add(new Expense(2, "Expense test 2", 25.99, LocalDate.now()));
        return (List<T>) expenses;
    }

    public boolean listToJson(File file, ArrayList listClass) throws IOException {
            return true;
        }
    }

