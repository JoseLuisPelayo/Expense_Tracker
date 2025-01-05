package cliCommands;

import cliCommands.sub.*;
import picocli.CommandLine;

import java.util.concurrent.Callable;

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


    public static void main(String[] args) {
        if (args.length == 0) {
        args = new String[1];
            args[0] = "-h";
        }
        int exitCode = new CommandLine(new ExpensesCommand()).execute(args);
        System.exit(exitCode);
    }

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
