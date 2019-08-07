package org.aikidistas.currencyexchange.infrastructure;

import org.aikidistas.currencyexchange.domain.exception.DomainException;
import org.aikidistas.currencyexchange.domain.text.TextSource;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommandLineArgumentsAsIsoCurrencyPairTextTest {

    private TextSource textSource;
    private String resultedCurrencyPairText;

    @Test
    public void commandLineArgumentsAsIsoCurrencyPairText() throws DomainException {
        String expectedCurrencyPairText = "EUR/DKK";

        givenCommandLineArgumentsAsIsoCurrencyPairTextWithParameters(expectedCurrencyPairText, "1");
        whenTextReceived();
        thenResultedCurrencyPairTextEquals(expectedCurrencyPairText);
    }

    @Test(expected = CommandLineApplicationException.class)
    public void exceptionWhenInvalidNumberOfArgumentsMoreThanTwo() throws DomainException {
        givenCommandLineArgumentsAsIsoCurrencyPairTextWithParameters("EUR/DKK", "1", "someOtherArgument");
        whenTextReceived();
    }

    @Test(expected = CommandLineApplicationException.class)
    public void exceptionWhenInvalidNumberOfArgumentsLessThanTwo() throws DomainException {
        givenCommandLineArgumentsAsIsoCurrencyPairTextWithParameters("EUR/DKK");
        whenTextReceived();
    }

    private void givenCommandLineArgumentsAsIsoCurrencyPairTextWithParameters(String... parameters) {
        textSource = new CommandLineArgumentsAsIsoCurrencyPairText(parameters);
    }

    private void whenTextReceived() throws DomainException {
        resultedCurrencyPairText = textSource.text();
    }

    private void thenResultedCurrencyPairTextEquals(String expectedCurrencyPairText) {
        assertEquals(expectedCurrencyPairText, resultedCurrencyPairText);
    }
}