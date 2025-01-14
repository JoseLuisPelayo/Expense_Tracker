package cliCommands.sub;

import javabean.Expense;
import picocli.CommandLine.*;
import repository.ExpenseRepository;
import service.ExpenseService;
import utils.JsonManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Callable;

/**
 * This command class is responsible for listing all expenses.
 * It uses the picocli library to handle command line arguments and options.
 */
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

    /**
     * Constructs a GetAllExpensesCommand and initializes the ExpenseService.
     *
     * @throws IOException if an I/O error occurs while initializing the ExpenseService
     */
    public GetAllExpensesCommand() throws IOException {
    }

    /**
     * Executes the list expenses command.
     * It retrieves and prints all expenses from the expense history.
     *
     * @return 0 if the command executed successfully
     */
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



