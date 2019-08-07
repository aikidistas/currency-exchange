package org.aikidistas.currencyexchange.domain.ratedata;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class CombinedRateDataTest {

    @Test
    public void ratedCurrencyPairs() {
        CombinedRateData combinedRateData = new CombinedRateData(
                new RateData.Fake(
                        new RatedCurrencyPair("EUR", "DKK", 2),
                        new RatedCurrencyPair("USD", "DKK", 4)
                ),
                new RateData.Fake(
                        new RatedCurrencyPair("DKK", "EUR", 0.5),
                        new RatedCurrencyPair("DKK", "USD", 0.25)
                )
        );

        Set<RatedCurrencyPair> actualPairs = combinedRateData.ratedCurrencyPairs();

        List<RatedCurrencyPair> expectedPairs = Arrays.asList(
                new RatedCurrencyPair("EUR", "DKK", 2),
                new RatedCurrencyPair("USD", "DKK", 4),
                new RatedCurrencyPair("DKK", "EUR", 0.5),
                new RatedCurrencyPair("DKK", "USD", 0.25)

        );

        assertTrue(expectedPairs.containsAll(actualPairs));
        assertTrue(actualPairs.containsAll(expectedPairs));
    }
}