package org.aikidistas.currencyexchange.domain.rate;

import org.aikidistas.currencyexchange.domain.currency.CurrencySource;
import org.aikidistas.currencyexchange.domain.exception.DomainException;
import org.aikidistas.currencyexchange.domain.ratedata.RateData;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InvertedRateTest {
    @Test
    public void invertedRate() {
        new InvertedRate(
                new CurrencySource.Fake("EUR"),
                new CurrencySource.Fake("DKK"),
                new RateData.Fake()
        );
    }

    @Test
    public void InvertedRateAssembleRate() throws DomainException {
        InvertedRate invertedRate = new InvertedRate(
                new CurrencySource.Fake("EUR"),
                new CurrencySource.Fake("DKK"),
                new RateData.Fake()
        );

        Rate rate = invertedRate.rate();
    }

    @Test
    public void InvertedRate_returns() throws DomainException {
        InvertedRate invertedRate = new InvertedRate(
                new CurrencySource.Fake("EUR"),
                new CurrencySource.Fake("DKK"),
                new RateData.Fake(0.1)
        );

        Rate rate = invertedRate.rate();
        assertEquals(new Rate.Dto(10), rate);
    }
}
