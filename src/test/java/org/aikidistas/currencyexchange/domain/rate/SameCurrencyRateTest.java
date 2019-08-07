package org.aikidistas.currencyexchange.domain.rate;

import org.aikidistas.currencyexchange.domain.currency.CurrencySource;
import org.aikidistas.currencyexchange.domain.exception.DomainException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SameCurrencyRateTest {
    @Test
    public void sameCurrencyRate() {
        new SameCurrencyRate(new CurrencySource.Fake("EUR"), new CurrencySource.Fake("EUR"));
    }

    @Test
    public void sameCurrencyRateReturnsRate() throws DomainException {
        RateSource rateSource = new SameCurrencyRate(
                new CurrencySource.Fake("EUR"),
                new CurrencySource.Fake("EUR")
        );
        Rate rate = rateSource.rate();
    }

    @Test
    public void sameCurrencyRateReturnsRateEqualOne() throws DomainException {
        RateSource rateSource = new SameCurrencyRate(
                new CurrencySource.Fake("EUR"),
                new CurrencySource.Fake("EUR")
        );
        Rate rate = rateSource.rate();
        assertEquals("1", rate.amount().textValue());
    }
}
