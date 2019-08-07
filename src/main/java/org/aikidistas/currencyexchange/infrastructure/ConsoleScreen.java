package org.aikidistas.currencyexchange.infrastructure;

import com.jcabi.aspects.Immutable;

import java.io.PrintWriter;

@Immutable
public class ConsoleScreen implements Screen {
    private final PrintWriter output;

    public ConsoleScreen() {
        this(new PrintWriter(System.out, true));
    }

    public ConsoleScreen(PrintWriter output) {
        this.output = output;
    }

    @Override
    public void show(String text) {
        output.println(text);
    }
}

