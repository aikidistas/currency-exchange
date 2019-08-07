package org.aikidistas.currencyexchange.domain.ratedata;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class InvertedRateDataTest {

    @Test
    public void ratedCurrencyPairs() {
        InvertedRateData invertedRateData = new InvertedRateData(new HashSet<>(Arrays.asList(
                new RatedCurrencyPair("EUR", "DKK", "7.4394"),
                new RatedCurrencyPair("USD", "DKK", "6.6311"),
                new RatedCurrencyPair("GBP", "DKK", "8.5285"),
                new RatedCurrencyPair("SEK", "DKK", "0.7610"),
                new RatedCurrencyPair("NOK", "DKK", "0.7840"),
                new RatedCurrencyPair("CHF", "DKK", "6.8358"),
                new RatedCurrencyPair("JPY", "DKK", "0.059740")
        )));

        Set<RatedCurrencyPair> actualInvertedPairs = invertedRateData.ratedCurrencyPairs();

        List<RatedCurrencyPair> expectedInvertedPairs = Arrays.asList(
                new RatedCurrencyPair("DKK", "EUR", "0.1344"),
                new RatedCurrencyPair("DKK", "USD", "0.1508"),
                new RatedCurrencyPair("DKK", "GBP", "0.1173"),
                new RatedCurrencyPair("DKK", "SEK", "1.3141"),
                new RatedCurrencyPair("DKK", "NOK", "1.2755"),
                new RatedCurrencyPair("DKK", "CHF", "0.1463"),
                new RatedCurrencyPair("DKK", "JPY", "16.7392")
        );
        assertTrue(actualInvertedPairs.containsAll(expectedInvertedPairs));
        assertTrue(expectedInvertedPairs.containsAll(actualInvertedPairs));
    }
}