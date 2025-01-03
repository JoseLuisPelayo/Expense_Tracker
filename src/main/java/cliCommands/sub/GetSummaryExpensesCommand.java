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


    public GetSummaryExpensesCommand() throws IOException {
    }

    @Option(
            names = {"-m", "--month"},
            description = "List the summary of the month expenses in the current year",
            paramLabel = "<month number 1 to 12>"
    )
    int month;

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
