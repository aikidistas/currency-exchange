package org.aikidistas.currencyexchange.infrastructure;

import org.aikidistas.currencyexchange.domain.exception.DomainException;
import org.aikidistas.currencyexchange.domain.text.TextSource;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommandLineArgumentsAsMoneyAmountTextTest {

    private TextSource textSource;
    private String resultedAmount;

    @Test
    public void commandLineArgumentsAsMoneyAmountText() throws DomainException {
        String expectedAmount = "1";

        givenCommandLineArgumentsAsMoneyAmountTextWithParameters("EUR/DKK", expectedAmount);
        whenTextReceived();
        thenResultedAmountEquals(expectedAmount);
    }

    @Test(expected = CommandLineApplicationException.class)
    public void exceptionWhenInvalidNumberOfArgumentsMoreThanTwo() throws DomainException {
        givenCommandLineArgumentsAsMoneyAmountTextWithParameters("EUR/DKK", "1", "someOtherArgument");
        whenTextReceived();
    }

    @Test(expected = CommandLineApplicationException.class)
    public void exceptionWhenInvalidNumberOfArgumentsLessThanTwo() throws DomainException {
        givenCommandLineArgumentsAsMoneyAmountTextWithParameters("EUR/DKK");
        whenTextReceived();
    }

    private void givenCommandLineArgumentsAsMoneyAmountTextWithParameters(String... parameters) {
        textSource = new CommandLineArgumentsAsMoneyAmountText(parameters);
    }

    private void whenTextReceived() throws DomainException {
        resultedAmount = textSource.text();
    }

    private void thenResultedAmountEquals(String expectedAmount) {
        assertEquals(expectedAmount, resultedAmount);
    }
}