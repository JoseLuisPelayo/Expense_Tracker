package cliCommands.sub;

import javabean.Expense;
import picocli.CommandLine;
import picocli.CommandLine.*;
import repository.ExpenseRepository;
import service.ExpenseService;
import utils.JsonManager;

import java.io.IOException;
import java.util.concurrent.Callable;

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
                names = {"-a", "--amount"},
                paramLabel = "<expense amount>"
        )
        double amount;


        @Override
    public Integer call() {
        Expense expense = serv.getExpenseById(expenseID);
        if (expense == null) {
            System.out.println("[update] There is no expense with ID " + expenseID);
            return 1;
        }

        if (amount > 0 || (description != null && !description.isBlank())) {
            if (amount > 0) expense.setAmount(amount);
            if (description != null && !description.isBlank()) expense.setDescription(description);

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
