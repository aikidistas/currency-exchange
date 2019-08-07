package org.aikidistas.currencyexchange.infrastructure;

import com.jcabi.aspects.Immutable;


@Immutable
public class ValidatedCommandLineArguments implements ArgumentsSource {
    private final ArgumentsSource argumentsSource;

    public ValidatedCommandLineArguments(ArgumentsSource argumentsSource) {
        this.argumentsSource = argumentsSource;
    }

    public String[] arguments() throws CommandLineApplicationException {
        String[] arguments = argumentsSource.arguments();
        validate(arguments);
        return arguments;
    }

    private void validate(String[] arguments) throws CommandLineApplicationException {
        if (invalidArgumentsLength(arguments)) {
            throw new CommandLineApplicationException();
        }
    }

    private boolean invalidArgumentsLength(String[] arguments) {
        return arguments.length != 2;
    }
}
