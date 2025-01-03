package cliCommands.sub;

import picocli.CommandLine;

import javabean.Expense;
import picocli.CommandLine.*;
import repository.ExpenseRepository;
import service.ExpenseService;
import utils.JsonManager;

import java.io.IOException;
import java.util.concurrent.Callable;

@Command(
        name= "find",
        mixinStandardHelpOptions = true,
        description = "This command find a expense by ID",
        requiredOptionMarker = '*',
        header = "Find expense by ID",
        footer = "%nDeveloped By Jose Luis G.Pelayo",
        optionListHeading = "Options are:%n",
        aliases = {"f"}
)

public class GetExpenseByIdCommand implements Callable<Integer> {
    ExpenseService serv = new ExpenseService(new ExpenseRepository(new JsonManager()));

    public GetExpenseByIdCommand() throws IOException {
    }

    @CommandLine.Parameters(
            index = "0",
            paramLabel = "<expense ID>"
    )
    int expenseID;

    public Integer call() {
        Expense expense = serv.getExpenseById(expenseID);
        if (expense != null) {
            System.out.println(expense);
            return 0;
        }

            System.out.println("Expense with ID " + expenseID + " not found");
            return 1;
    }
}
