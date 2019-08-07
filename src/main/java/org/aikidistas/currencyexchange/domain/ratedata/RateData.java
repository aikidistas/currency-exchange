package org.aikidistas.currencyexchange.domain.ratedata;

import com.jcabi.aspects.Immutable;
import org.aikidistas.currencyexchange.domain.rate.Rate;

import java.util.Arrays;
import java.util.Currency;
import java.util.LinkedHashSet;
import java.util.Set;

public interface RateData {

    Set<RatedCurrencyPair> ratedCurrencyPairs();

    @Immutable
    final class Fake implements RateData {
        private final Set<RatedCurrencyPair> ratedCurrencyPairs;

        public Fake() {
            this(new Rate.Dto(1));
        }

        public Fake(double constantRate) {
            this(new Rate.Dto(constantRate));
        }

        public Fake(int constantRate) {
            this(new Rate.Dto(constantRate));
        }

        public Fake(Rate constantRate) {
            this(allPossibleRatedCurrencyPairs(constantRate));
        }

        public Fake(RatedCurrencyPair... ratedCurrencyPairs) {
            this(new LinkedHashSet<>(Arrays.asList(ratedCurrencyPairs)));
        }

        public Fake(Set<RatedCurrencyPair> ratedCurrencyPairs) {
            this.ratedCurrencyPairs = ratedCurrencyPairs;
        }

        private static Set<RatedCurrencyPair> allPossibleRatedCurrencyPairs(Rate rate) {
            Set<RatedCurrencyPair> allRatedCurrencyPairs = newEmptyRatedCurrencyPairSet();
            for (Currency mainCurrency : Currency.getAvailableCurrencies()) {
                addCurrencyPairsFromMainCurrencyToGivenCurrencyPairsSet(allRatedCurrencyPairs, mainCurrency, rate);
            }
            return allRatedCurrencyPairs;
        }

        private static void addCurrencyPairsFromMainCurrencyToGivenCurrencyPairsSet(
                Set<RatedCurrencyPair> allRatedCurrencyPairs,
                Currency mainCurrency,
                Rate rate
        ) {
            allRatedCurrencyPairs.addAll(ratedCurrencyPairsForMainCurrency(mainCurrency, rate));
        }

        private static Set<RatedCurrencyPair> ratedCurrencyPairsForMainCurrency(Currency mainCurrency, Rate rate) {
            Set<RatedCurrencyPair> ratedPairsForMainCurrency = newEmptyRatedCurrencyPairSet();
            for (Currency moneyCurrency : Currency.getAvailableCurrencies()) {
                addCurrencyPairToGivenSet(ratedPairsForMainCurrency, mainCurrency, moneyCurrency, rate);
            }
            return ratedPairsForMainCurrency;
        }

        private static void addCurrencyPairToGivenSet(
                Set<RatedCurrencyPair> ratedPairsForMainCurrency,
                Currency mainCurrency,
                Currency moneyCurrency,
                Rate rate
        ) {
            ratedPairsForMainCurrency.add(new RatedCurrencyPair(mainCurrency, moneyCurrency, rate));
        }

        private static LinkedHashSet<RatedCurrencyPair> newEmptyRatedCurrencyPairSet() {
            return new LinkedHashSet<>();
        }

        @Override
        public Set<RatedCurrencyPair> ratedCurrencyPairs() {
            return ratedCurrencyPairs;
        }
    }
}
