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
        name= "add",
        mixinStandardHelpOptions = true,
        description = "This command add a expense to our history",
        requiredOptionMarker = '*',
        header = "Update a expense",
        footer = "%nDeveloped By Jose Luis G.Pelayo",
        optionListHeading = "%nOptions are:%n"
)

public class AddExpenseCommand implements Callable<Integer> {
    ExpenseService serv = new ExpenseService(new ExpenseRepository(new JsonManager()));

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
            names = {"-d", "--date"},
            paramLabel = "<expense date>"
    )
    LocalDate date;

    @Override
    public Integer call() throws IOException {
        Expense expense = new Expense(
                (serv.getAllExpenses().isEmpty()) ? 1 : serv.getAllExpenses().getLast().getId() + 1,
                description,
                amount,
                LocalDate.now()
        );

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
