package org.aikidistas.currencyexchange.domain.currency;

import org.aikidistas.currencyexchange.domain.exception.DomainException;
import org.aikidistas.currencyexchange.domain.exception.UnsupportedCurrencyPairException;
import org.aikidistas.currencyexchange.domain.text.Text;
import org.junit.Test;

import java.util.Currency;

import static org.junit.Assert.assertEquals;

public class CurrencyPairTextAsMoneyCurrencyTest {
    @Test
    public void isoCurrencyPairTextAsMoneyCurrency() throws DomainException {
        CurrencySource moneyCurrencySource = new CurrencyPairTextAsMoneyCurrency(new Text("EUR/DKK"));
        Currency expectedMoneyCurrency = Currency.getInstance("DKK");
        assertEquals(expectedMoneyCurrency, moneyCurrencySource.currency());

    }

    @Test(expected = UnsupportedCurrencyPairException.class)
    public void givenInvalidCurrencyPairText_whenCurrencyIsExecuted_throwException() throws DomainException {
        CurrencySource currencySource = new CurrencyPairTextAsMoneyCurrency(new Text("EUR/DKKasdf"));
        currencySource.currency();
    }
}