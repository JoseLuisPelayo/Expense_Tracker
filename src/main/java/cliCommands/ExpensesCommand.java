package cliCommands;

import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(
        name= "xpenseTracker",
        mixinStandardHelpOptions = true,
        version = "1.0.0",
        description = "This is a expense tracker tool which help us to manage and monitor our expenses",
        requiredOptionMarker = '*',
        header = "Xpense Tracker",
        optionListHeading = "%nOptions are:%n"
)
public class ExpensesCommand implements Callable<Integer> {
    private static final Integer SUCCESS = 0;
    private static final Integer FAILURE = 1;


//    @CommandLine.Option(
//            names = {"-u", "--user"},
//            required = true,
//            description = "Provide user",
//            paramLabel = "<user name>"
//    )
//    String user;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new ExpensesCommand()).execute("-h");
        System.exit(exitCode);
    }

    public Integer call() throws Exception {
        System.out.println("[Xpense Tracker] Hello to Expense Tracker");
        return SUCCESS;
    }
}
