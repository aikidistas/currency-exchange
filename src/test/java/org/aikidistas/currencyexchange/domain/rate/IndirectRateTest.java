package org.aikidistas.currencyexchange.domain.rate;

import org.aikidistas.currencyexchange.domain.currency.CurrencySource;
import org.aikidistas.currencyexchange.domain.exception.DomainException;
import org.aikidistas.currencyexchange.domain.exception.UnsupportedCurrencyPairException;
import org.aikidistas.currencyexchange.domain.ratedata.RateData;
import org.aikidistas.currencyexchange.domain.ratedata.RatedCurrencyPair;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IndirectRateTest {
    @Test
    public void indirectRate() {
        new IndirectRate(
                new CurrencySource.Fake("EUR"),
                new CurrencySource.Fake("USD"),
                new RateData.Fake()
        );
    }

    @Test
    public void InvertedRateAssembleRate() throws DomainException {
        IndirectRate indirectRate = new IndirectRate(
                new CurrencySource.Fake("EUR"),
                new CurrencySource.Fake("USD"),
                new RateData.Fake()
        );

        Rate rate = indirectRate.rate();
    }

    @Test
    public void indirectRateAssembleRate_returnsAnyRateCombinedFromMultipleCurrencyPairRates() throws DomainException {
        IndirectRate indirectRate = new IndirectRate(
                new CurrencySource.Fake("EUR"),
                new CurrencySource.Fake("USD"),
                new RateData.Fake(
                        new RatedCurrencyPair("EUR", "DKK", 2),
                        new RatedCurrencyPair("DKK", "USD", 3)
                )
        );

        Rate actualRate = indirectRate.rate();

        Rate expectedRate = new Rate.Dto(6);
        assertEquals(expectedRate, actualRate);
    }

    @Test(expected = UnsupportedCurrencyPairException.class)
    public void givenIndirectRateWithNotListedCurrency_throwsException() throws DomainException {
        IndirectRate indirectRate = new IndirectRate(
                new CurrencySource.Fake("EUR"),
                new CurrencySource.Fake("USD"),
                new RateData.Fake(
                        new RatedCurrencyPair("EUR", "DKK", 2),
                        new RatedCurrencyPair("DKK", "JPY", 3)
                )
        );

        indirectRate.rate();
    }

    @Test(expected = UnsupportedCurrencyPairException.class)
    public void givenIndirectRateWithoutIndirectlyConnectedPairs_throwsException() throws DomainException {
        IndirectRate indirectRate = new IndirectRate(
                new CurrencySource.Fake("EUR"),
                new CurrencySource.Fake("USD"),
                new RateData.Fake(
                        new RatedCurrencyPair("EUR", "DKK", 2),
                        new RatedCurrencyPair("DKK", "JPY", 3),
                        new RatedCurrencyPair("USD", "SEK", 3)
                )
        );

        indirectRate.rate();
    }
}
