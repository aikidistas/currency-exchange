package org.aikidistas.currencyexchange.domain.ratedata;

import org.aikidistas.currencyexchange.domain.rate.Rate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class RateDataForHundred implements RateData {
    private final Set<RatedCurrencyPair> originalRateForHundred;

    public RateDataForHundred(RatedCurrencyPair... originalRateForHundred) {
        this(new HashSet<>(Arrays.asList(originalRateForHundred)));
    }

    public RateDataForHundred(Set<RatedCurrencyPair> originalRateForHundred) {
        this.originalRateForHundred = originalRateForHundred;
    }

    @Override
    public Set<RatedCurrencyPair> ratedCurrencyPairs() {
        return originalRateForHundred.stream()
                .map(this::currencyPairForHundredToPairForOne)
                .collect(Collectors.toSet());
    }

    private RatedCurrencyPair currencyPairForHundredToPairForOne(RatedCurrencyPair pair) {
        return new RatedCurrencyPair(
                pair.mainCurrency(),
                pair.moneyCurrency(),
                pair.rate().combinedRate(new Rate.Dto("0.01")));
    }
}
