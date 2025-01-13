package cliCommands.sub;

import picocli.CommandLine.*;
import repository.ExpenseRepository;
import service.ExpenseService;
import utils.JsonManager;

import java.io.IOException;
import java.util.concurrent.Callable;

/**
 * This command class is responsible for deleting an expense from the history.
 * It uses the picocli library to handle command line arguments and options.
 */
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

    /**
     * Constructs a DeleteExpenseCommand and initializes the ExpenseService.
     *
     * @throws IOException if an I/O error occurs while initializing the ExpenseService.
     */
    public DeleteExpenseCommand() throws IOException {
    }

    @Parameters(
            index = "0",
            paramLabel = "<expense ID>"
    )
    int id;

    /**
     * Executes the delete expense command.
     * It deletes the expense with the specified id from the expense history.
     *
     * @return 0 if the expense was successfully deleted, 1 otherwise
     */
    public Integer call() {
        if (serv.deleteExpense(id)) {
            System.out.println("Successfully deleted expense with id:" + id);
            return 0;
        }

        System.out.println("Expense with id:" + id + " not found");
        return 1;
    }

}
