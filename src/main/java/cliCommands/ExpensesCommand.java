package cliCommands;

import cliCommands.sub.*;
import picocli.CommandLine;

import java.util.concurrent.Callable;

/**
 * This is the main command class for the Xpense Tracker CLI application.
 * It uses the picocli library to manage and execute subcommands for managing and monitoring expenses.
 */
@CommandLine.Command(
        name= "xpenseTracker",
        mixinStandardHelpOptions = true,
        version = "1.0.0",
        description = "This is a expense tracker tool which help us to manage and monitor our expenses",
        requiredOptionMarker = '*',
        header = "Xpense Tracker",
        footer = "%nDeveloped By Jose Luis G.Pelayo",
        optionListHeading = "Options are:%n",
        commandListHeading = "Commands are:%n",
        subcommands = {
                UpdateExpenseCommand.class,
                AddExpenseCommand.class,
                DeleteExpenseCommand.class,
                GetExpenseByIdCommand.class,
                GetAllExpensesCommand.class,
                GetSummaryExpensesCommand.class
        }
)
public class ExpensesCommand implements Callable<Integer> {
    private static final Integer SUCCESS = 0;
    private static final Integer FAILURE = 1;

    /**
     * This method is called when the main command is executed.
     * It prints a welcome message and returns a success status.
     *
     * @return the exit status code (0 for success, 1 for failure)
     */
    public Integer call() {
        try {
        System.out.println("[Xpense Tracker] Hello to Expense Tracker");
        return SUCCESS;
        } catch (Exception e) {
            System.out.println("[Xpense Tracker] Error: " + e.getMessage());
            return FAILURE;
        }
    }
}
