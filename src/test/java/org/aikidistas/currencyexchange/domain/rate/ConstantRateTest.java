package org.aikidistas.currencyexchange.domain.rate;

import org.aikidistas.currencyexchange.domain.currency.CurrencySource;
import org.aikidistas.currencyexchange.domain.exception.DomainException;
import org.aikidistas.currencyexchange.domain.ratedata.RateData;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConstantRateTest {
    @Test
    public void constantRate() {
        new ConstantRate(
                new CurrencySource.Fake("EUR"),
                new CurrencySource.Fake("DKK"),
                new RateData.Fake()
        );
    }

    @Test
    public void constantRateAssembleRate() throws DomainException {
        ConstantRate constantRate = new ConstantRate(
                new CurrencySource.Fake("EUR"),
                new CurrencySource.Fake("DKK"),
                new RateData.Fake(1)
        );

        Rate rate = constantRate.rate();
        Rate expectedRate = new Rate.Dto(1);

        assertEquals(expectedRate, rate);
    }
}
