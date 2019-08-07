package org.aikidistas.currencyexchange.infrastructure;

public interface ArgumentsSource {
    String[] arguments() throws CommandLineApplicationException;

    final class Dto implements ArgumentsSource {
        private final String[] arguments;

        public Dto(String[] arguments) {
            this.arguments = arguments;
        }

        @Override
        public String[] arguments() {
            return arguments;
        }
    }
}
