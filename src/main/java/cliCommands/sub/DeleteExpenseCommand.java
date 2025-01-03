package cliCommands.sub;

import javabean.Expense;
import picocli.CommandLine;
import picocli.CommandLine.*;
import repository.ExpenseRepository;
import service.ExpenseService;
import utils.JsonManager;

import java.io.IOException;
import java.time.LocalDate;
import java.util.concurrent.Callable;

@Command(
        name= "remove",
        mixinStandardHelpOptions = true,
        description = "This command delete a expense of our history",
        requiredOptionMarker = '*',
        header = "Remove a expense",
        footer = "%nDeveloped By Jose Luis G.Pelayo",
        optionListHeading = "%nOptions are:%n",
        aliases = {"rm", "delete", "del"}
)
public class DeleteExpenseCommand implements Callable<Integer> {
    ExpenseService serv = new ExpenseService(new ExpenseRepository(new JsonManager()));
    public DeleteExpenseCommand() throws IOException {
    }

    @Parameters(
            index = "0",
            paramLabel = "<expense ID>"
    )
    int id;


    public Integer call() {
        if (serv.deleteExpense(id)) {
            System.out.println("Successfully deleted expense with id:" + id);
            return 0;
        }

        System.out.println("Expense with id:" + id + " not found");
        return 1;
    }

}
