package org.aikidistas.currencyexchange.domain.text;

import org.aikidistas.currencyexchange.domain.exception.DomainException;
import org.aikidistas.currencyexchange.domain.exception.UnsupportedCurrencyPairException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ValidatedCurrencyPairTextTest {

    @Test
    public void text() throws DomainException {
        String expectedText = "EUR/DKK";
        ValidatedCurrencyPairText validatedText = new ValidatedCurrencyPairText(new Text(expectedText));
        assertEquals(expectedText, validatedText.text());
    }

    @Test(expected = UnsupportedCurrencyPairException.class)
    public void invalidLongText() throws DomainException {
        String invalidText = "EUR/DKKasdf";
        ValidatedCurrencyPairText validatedText = new ValidatedCurrencyPairText(new Text(invalidText));
        validatedText.text();
    }

    @Test(expected = UnsupportedCurrencyPairException.class)
    public void invalidTextWithoutSlashSeparator() throws DomainException {
        String invalidText = "EUR DKK";
        ValidatedCurrencyPairText validatedText = new ValidatedCurrencyPairText(new Text(invalidText));
        validatedText.text();
    }
}