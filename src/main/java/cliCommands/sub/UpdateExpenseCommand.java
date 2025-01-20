package cliCommands.sub;

import javabean.Expense;
import picocli.CommandLine;
import picocli.CommandLine.*;
import repository.ExpenseRepository;
import service.ExpenseService;
import utils.JsonManager;

import java.io.IOException;
import java.util.concurrent.Callable;

/**
 * This command class is responsible for updating an expense.
 * It uses the picocli library to handle command line arguments and options.
 */
    @Command(
            name= "update",
            mixinStandardHelpOptions = true,
            description = "This command update a expense",
            requiredOptionMarker = '*',
            header = "Update expense",
            footer = "%nDeveloped By Jose Luis G.Pelayo",
            optionListHeading = "Options are:%n"
    )

public class UpdateExpenseCommand implements Callable<Integer> {
        ExpenseService serv = new ExpenseService(new ExpenseRepository(new JsonManager()));

    /**
     * Constructs an UpdateExpenseCommand.
     *
     * @throws IOException if an I/O error occurs while initializing the ExpenseService
     */
        public UpdateExpenseCommand() throws IOException {
        }

        @CommandLine.Parameters(
                index = "0",
                paramLabel = "<expense ID>"
        )
        int expenseID;

        @CommandLine.Option(
                names = {"-d", "--description"},
                paramLabel = "<expense description>"
        )
        String description;

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
                names = {"-a", "--amount"},
                paramLabel = "<expense amount>"
        )
        double amount;

    /**
     * Executes the update expense command.
     * It retrieves, updates, and saves the expense with the specified ID.
     *
     * @return 0 if the expense was successfully updated, 1 otherwise
     */
        @Override
    public Integer call() {
        Expense expense = serv.getExpenseById(expenseID);
        if (expense == null) {
            System.out.println("[update] There is no expense with ID " + expenseID);
            return 1;
        }

        if (
                amount > 0 ||
                (description != null && !description.isBlank()) ||
                (category != null && !category.isBlank())
                ) {
            if (amount > 0) expense.setAmount(amount);
            if (description != null && !description.isBlank()) expense.setDescription(description);
            if (category != null && !category.isEmpty()) {
                if (!expense.selectCategory(category))
                    System.err.println(
                            "This category: " + category + " does not exist \n" +
                                    "The expense be added to others category"
                    );
                expense.setCategory(Expense.Category.Others);
            }

            Expense updatedExpense = serv.updateExpense(expense);

            if (updatedExpense == null) {
                System.out.println("[update] Error on updating expense with ID " + expenseID);
                System.out.println("[update] try it again");
                return 1;
            }

            System.out.println("[update] updating the expense ");
            System.out.println(expense);
            return 0;

        } else {
            System.out.println("[update] Failed to update, need any optional argument to update the expense");
            return 1;
        }
    }
}
