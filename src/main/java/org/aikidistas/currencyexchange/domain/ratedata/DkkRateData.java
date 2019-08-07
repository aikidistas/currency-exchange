package org.aikidistas.currencyexchange.domain.ratedata;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DkkRateData implements RateData {

    @Override
    public Set<RatedCurrencyPair> ratedCurrencyPairs() {
        List<RatedCurrencyPair> ratedCurrencyPairs = Arrays.asList(
                new RatedCurrencyPair("EUR", "DKK", 7.4394),
                new RatedCurrencyPair("USD", "DKK", 6.6311),
                new RatedCurrencyPair("GBP", "DKK", 8.5285),
                new RatedCurrencyPair("SEK", "DKK", 0.7610),
                new RatedCurrencyPair("NOK", "DKK", 0.7840),
                new RatedCurrencyPair("CHF", "DKK", 6.8358),
                new RatedCurrencyPair("JPY", "DKK", 0.059740)
        );
        return new HashSet<>(
                ratedCurrencyPairs
        );
    }
}
