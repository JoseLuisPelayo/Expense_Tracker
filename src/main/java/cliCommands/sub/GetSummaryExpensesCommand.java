package cliCommands.sub;

import picocli.CommandLine.*;
import repository.ExpenseRepository;
import service.ExpenseService;
import utils.JsonManager;

import java.io.IOException;
import java.util.concurrent.Callable;

/**
 * This command class is responsible for providing a summary of expenses.
 * It uses the picocli library to handle command line arguments and options.
 */
@Command(
        name = "summary",
        mixinStandardHelpOptions = true,
        description = "This command provide the summary of  expenses",
        requiredOptionMarker = '*',
        header = "Summary expenses",
        footer = "%nDeveloped By Jose Luis G.Pelayo",
        optionListHeading = "%nOptions are:%n",
        aliases = {"sum"}
)

public class GetSummaryExpensesCommand implements Callable<Integer> {
    ExpenseService serv = new ExpenseService(new ExpenseRepository(new JsonManager()));

    /**
     * Constructs a GetSummaryExpensesCommand.
     *
     * @throws IOException if an I/O error occurs while initializing the ExpenseService
     */
    public GetSummaryExpensesCommand() throws IOException {
    }

    @Option(
            names = {"-m", "--month"},
            description = "List the summary of the month expenses in the current year",
            paramLabel = "<month number 1 to 12>"
    )
    int month;

    /**
     * Executes the summary expenses command.
     * It retrieves and prints the summary of expenses, either for the entire year or for a specific month if specified.
     *
     * @return 0 if the command executed successfully
     */
    public Integer call() {
        if (month == 0) {
            System.out.println("The summary of the expenses are: " + serv.getExpensesSummary());
            return 0;
        }

        if (month > 12 || month < 1) {
            System.out.println("Month number must be between 1 and 12");
            return 0;
        }

        System.out.println("The summary of the month " + month + " expenses are: " + serv.getExpensesSummary(month));
        return 0;
    }

}
