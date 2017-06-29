package com.sysgears.simplecalculator.ui;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Contains all the application commands
 */
public enum Commands {
    EXIT("exit", "", " - to close the application"),
    HELP("help", "You can use next commands:", " - to get this description"),
    HISTORY("history", "History:", " - to see the calculation history"),
    UNIQUE_HISTORY("history unique", "Unique history:", " - to see the calculation history without duplicates"),
    OPERATORS("operators", "The operators list, sorted in ascending order by their precedence:",
            " - to see the operators list");

    public final String COMMAND;
    public final String HEADER;
    public final String DESCRIPTION;

    /**
     * Constructs an object
     *
     * @param COMMAND The string representation of command
     * @param HEADER The command header for the UI
     * @param DESCRIPTION  The command description
     */
    Commands(final String COMMAND, final String HEADER, final String DESCRIPTION) {
        this.COMMAND = COMMAND;
        this.HEADER = HEADER;
        this.DESCRIPTION = DESCRIPTION;
    }

    /**
     * Builds and returns the string representation of the command list
     *
     * @return The string with the description of all the commands
     */
    public static String getList() {
        return Stream.of(values()).
                map(e -> "\t" + e.COMMAND + e.DESCRIPTION).
                collect(Collectors.joining(System.lineSeparator()));
    }

    /**
     * Returns the command header with added a line separator
     *
     * @return The command header string
     */
    public String getHEADER() {
        return HEADER + System.lineSeparator();
    }
}