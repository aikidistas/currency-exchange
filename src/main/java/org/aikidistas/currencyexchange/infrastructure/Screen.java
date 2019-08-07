package org.aikidistas.currencyexchange.infrastructure;

public interface Screen {
    void show(String text);

    final class Fake implements Screen {
        private final StringBuilder output = new StringBuilder();

        @Override
        public void show(String text) {
            output.append(text).append(System.lineSeparator());
        }

        public String output() {
            return output.toString();
        }
    }
}

