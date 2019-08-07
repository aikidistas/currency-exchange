package org.aikidistas.currencyexchange.infrastructure;

import com.jcabi.aspects.Immutable;
import org.aikidistas.currencyexchange.domain.text.TextSource;

@Immutable
public class CommandLineArgumentsAsIsoCurrencyPairText implements TextSource {
    private final ArgumentsSource argumentsSource;

    public CommandLineArgumentsAsIsoCurrencyPairText(String... argumentsSource) {
        this(new ArgumentsSource.Dto(argumentsSource));
    }

    public CommandLineArgumentsAsIsoCurrencyPairText(ArgumentsSource argumentsSource) {
        this.argumentsSource = new ValidatedCommandLineArguments(argumentsSource);
    }

    @Override
    public String text() throws CommandLineApplicationException {
        return argumentsSource.arguments()[0];
    }
}
