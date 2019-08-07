package org.aikidistas.currencyexchange.domain.ratedata;

import org.aikidistas.currencyexchange.domain.rate.Rate;
import org.junit.Test;

import java.util.Arrays;
import java.util.Currency;

import static org.junit.Assert.assertTrue;

public class DkkRateDataTest {
    @Test
    public void dkkRateData() {
        RateData dkkRateData = new DkkRateData();
        assertTrue(dkkRateData.ratedCurrencyPairs().containsAll(Arrays.asList(
                new RatedCurrencyPair(Currency.getInstance("EUR"), Currency.getInstance("DKK"), new Rate.Dto("7.4394")),
                new RatedCurrencyPair(Currency.getInstance("USD"), Currency.getInstance("DKK"), new Rate.Dto("6.6311")),
                new RatedCurrencyPair(Currency.getInstance("GBP"), Currency.getInstance("DKK"), new Rate.Dto("8.5285")),
                new RatedCurrencyPair(Currency.getInstance("SEK"), Currency.getInstance("DKK"), new Rate.Dto("0.7610")),
                new RatedCurrencyPair(Currency.getInstance("NOK"), Currency.getInstance("DKK"), new Rate.Dto("0.7840")),
                new RatedCurrencyPair(Currency.getInstance("CHF"), Currency.getInstance("DKK"), new Rate.Dto("6.8358")),
                new RatedCurrencyPair(Currency.getInstance("JPY"), Currency.getInstance("DKK"), new Rate.Dto("0.059740"))
        )));
    }
}
