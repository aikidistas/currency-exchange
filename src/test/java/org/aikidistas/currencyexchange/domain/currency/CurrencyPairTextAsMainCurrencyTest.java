package org.aikidistas.currencyexchange.domain.currency;

import org.aikidistas.currencyexchange.domain.exception.DomainException;
import org.aikidistas.currencyexchange.domain.exception.UnsupportedCurrencyPairException;
import org.aikidistas.currencyexchange.domain.text.Text;
import org.junit.Test;

import java.util.Currency;

import static org.junit.Assert.assertEquals;

public class CurrencyPairTextAsMainCurrencyTest {
    @Test
    public void isoCurrencyPairTextAsMainCurrency() throws DomainException {
        CurrencySource currencySource = new CurrencyPairTextAsMainCurrency(new Text("EUR/DKK"));
        Currency expectedMainCurrency = Currency.getInstance("EUR");
        assertEquals(expectedMainCurrency, currencySource.currency());
    }

    @Test(expected = UnsupportedCurrencyPairException.class)
    public void givenInvalidCurrencyPairText_whenCurrencyIsExecuted_throwException() throws DomainException {
        CurrencySource currencySource = new CurrencyPairTextAsMainCurrency(new Text("EUR/DKKasdf"));
        currencySource.currency();
    }
}
