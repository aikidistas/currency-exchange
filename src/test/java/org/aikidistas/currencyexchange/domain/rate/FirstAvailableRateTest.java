package org.aikidistas.currencyexchange.domain.rate;

import org.aikidistas.currencyexchange.domain.exception.DomainException;
import org.aikidistas.currencyexchange.domain.exception.UnsupportedCurrencyPairException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FirstAvailableRateTest {
    @Test
    public void firstAvailableRate() {
        new FirstAvailableRate(
                new RateSource.Fake()
        );
    }

    @Test
    public void firstAvailableRateFromTwoCurrencyRates() {
        new FirstAvailableRate(
                new RateSource.Fake(),
                new RateSource.Fake()
        );
    }

    @Test
    public void firstAvailableRateFromThreeCurrencyRates() {
        new FirstAvailableRate(
                new RateSource.Fake(),
                new RateSource.Fake(),
                new RateSource.Fake()
        );
    }

    @Test
    public void firstAvailableRateReturnsRate() throws DomainException {
        FirstAvailableRate firstAvailableRate = new FirstAvailableRate(
                new RateSource.Fake()
        );

        Rate rate = firstAvailableRate.rate();
    }

    @Test
    public void firstAvailableRateReturnsGivenRate() throws DomainException {
        FirstAvailableRate firstAvailableRate = new FirstAvailableRate(
                new RateSource.Fake(2)
        );

        Rate rate = firstAvailableRate.rate();
        assertEquals(new Rate.Dto(2), rate);
    }

    @Test
    public void firstAvailableRateSkipsUnavailableRates() throws DomainException {
        FirstAvailableRate firstAvailableRate = new FirstAvailableRate(
                new RateSource.FakeUnavailable(),
                new RateSource.Fake(3)
        );

        Rate rate = firstAvailableRate.rate();
        assertEquals(new Rate.Dto(3), rate);
    }

    @Test(expected = UnsupportedCurrencyPairException.class)
    public void firstAvailableRateThrowExceptionWhenAllRateSourcesAreUnavailable() throws DomainException {
        FirstAvailableRate firstAvailableRate = new FirstAvailableRate(
                new RateSource.FakeUnavailable(),
                new RateSource.FakeUnavailable(),
                new RateSource.FakeUnavailable(),
                new RateSource.FakeUnavailable()
        );

        firstAvailableRate.rate();
    }
}
