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

/**
 * This command class is responsible for adding an expense to the history.
 * It uses the picocli library to handle command line arguments and options.
 */
@Command(
        name= "add",
        mixinStandardHelpOptions = true,
        description = "This command add a expense to our history",
        requiredOptionMarker = '*',
        header = "Add a expense",
        footer = "%nDeveloped By Jose Luis G.Pelayo",
        optionListHeading = "%nOptions are:%n"
)

public class AddExpenseCommand implements Callable<Integer> {
    ExpenseService serv = new ExpenseService(new ExpenseRepository(new JsonManager()));

    /**
     * Constructs an AddExpenseCommand and initializes the ExpenseService.
     *
     * @throws IOException if an I/O error occurs while initializing the ExpenseService
     */
    public AddExpenseCommand() throws IOException {
    }

    @CommandLine.Parameters(
            index = "0",
            paramLabel = "<expense description>"
    )
    String description;

    @CommandLine.Parameters(
            index = "1",
            paramLabel = "<expense amount>"
    )
    double amount;

    @CommandLine.Option(
            names = {"-c", "--category"},
            paramLabel = "<expense category>",
            description = """
                    set the category of the expense
                     possible values:
                        -GroceriesLeisure
                        -Electronics
                        -Utilities
                        -Clothing
                        -Health
                        -Others
                    """
    )
    String category;

    @CommandLine.Option(
            names = {"-d", "--date"},
            paramLabel = "<expense date>"
    )
    LocalDate date;

    /**
     * Executes the add expense command.
     * It creates a new Expense object and adds it to the expense history.
     *
     * @return 0 if the expense was successfully added, 1 otherwise
     * @throws IOException if an I/O error occurs while adding the expense
     */
    @Override
    public Integer call() throws IOException {
        Expense expense = new Expense();
        expense.setId(serv.getAllExpenses().isEmpty() ? 1 : serv.getAllExpenses().getLast().getId() + 1);
        expense.setDescription(description);
        expense.setAmount(amount);
        expense.setDate(date);
        expense.setCategory(Expense.Category.Others);

        if (date == null) date = LocalDate.now();

        if (category != null && !category.isEmpty()) {
           if (!expense.selectCategory(category))
               System.err.println(
                       "This category: " + category + " does not exist \n" +
                       "The expense be added to others category"
               );
            expense.setCategory(Expense.Category.Others);
        }

        if (date != null)
            expense.setDate(date);

        if (serv.addExpense(expense)) {
            System.out.println("[Add] Adding expense");
            System.out.println(expense);
            return 0;
        } else {
            System.out.println("[Add] Failed to add expense");
            return 1;
        }
    }
}
