package org.aikidistas.currencyexchange.domain.ratedata;

import com.jcabi.aspects.Immutable;

import java.util.HashSet;
import java.util.Set;

@Immutable
public class InvertedRateData implements RateData {
    private final Set<RatedCurrencyPair> originalRatedCurrencyPairs;

    public InvertedRateData(RateData originalRateData) {
        this(originalRateData.ratedCurrencyPairs());
    }

    public InvertedRateData(Set<RatedCurrencyPair> originalRatedCurrencyPairs) {
        this.originalRatedCurrencyPairs = originalRatedCurrencyPairs;
    }

    @Override
    public Set<RatedCurrencyPair> ratedCurrencyPairs() {
        Set<RatedCurrencyPair> invertedPairsRates = new HashSet<>();
        originalRatedCurrencyPairs.forEach(pair -> addInvertedCurrencyPairToGivenSet(invertedPairsRates, pair));
        return invertedPairsRates;
    }

    private boolean addInvertedCurrencyPairToGivenSet(Set<RatedCurrencyPair> invertedPairsRates, RatedCurrencyPair pair) {
        return invertedPairsRates.add(
                new RatedCurrencyPair(pair.moneyCurrency(), pair.mainCurrency(), pair.rate().inverted())
        );
    }
}
