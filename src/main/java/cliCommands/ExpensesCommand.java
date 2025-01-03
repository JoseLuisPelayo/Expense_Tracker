package cliCommands;

import cliCommands.sub.AddExpenseCommand;
import picocli.CommandLine;

import java.time.LocalDate;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name= "xpenseTracker",
        mixinStandardHelpOptions = true,
        version = "1.0.0",
        description = "This is a expense tracker tool which help us to manage and monitor our expenses",
        requiredOptionMarker = '*',
        header = "Xpense Tracker",
        footer = "%nDeveloped By Jose Luis G.Pelayo",
        optionListHeading = "%nOptions are:",
        commandListHeading = "%nCommands are:",
        subcommands = {
                AddExpenseCommand.class
        }
)
public class ExpensesCommand implements Callable<Integer> {
    private static final Integer SUCCESS = 0;
    private static final Integer FAILURE = 1;


    public static void main(String[] args) {
        int exitCode = new CommandLine(new ExpensesCommand()).execute("add", "", "22.55");
        System.exit(exitCode);
    }

    public Integer call() throws Exception {
        System.out.println("[Xpense Tracker] Hello to Expense Tracker");
        return SUCCESS;
    }
}
