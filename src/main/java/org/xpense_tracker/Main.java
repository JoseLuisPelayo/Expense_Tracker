package org.xpense_tracker;

import cliCommands.ExpensesCommand;
import picocli.CommandLine;


public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            args = new String[1];
            args[0] = "-h";
        }
        int exitCode = new CommandLine(new ExpensesCommand()).execute(args);
        System.exit(exitCode);
    }
}