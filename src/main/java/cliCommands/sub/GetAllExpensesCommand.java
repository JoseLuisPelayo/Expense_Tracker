package cliCommands.sub;

import picocli.CommandLine;

import javabean.Expense;
import picocli.CommandLine.*;
import repository.ExpenseRepository;
import service.ExpenseService;
import utils.JsonManager;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.Callable;

@Command(
        name= "list",
        mixinStandardHelpOptions = true,
        description = "This command list all expenses",
        requiredOptionMarker = '*',
        header = "List expenses",
        footer = "%nDeveloped By Jose Luis G.Pelayo",
        optionListHeading = "Options are:%n",
        aliases = {"ls"}
)

public class GetAllExpensesCommand implements Callable<Integer> {
    ExpenseService serv = new ExpenseService(new ExpenseRepository(new JsonManager()));

    public GetAllExpensesCommand() throws IOException {
    }

    public Integer call() {
        ArrayList<Expense> expenses = serv.getAllExpenses();
            if (expenses.isEmpty()) {
                System.out.println("No expenses found");
                return 0;
            }

            for (Expense expense : expenses) {
                System.out.println(expense);
            }
            return 0;
        }

    }



