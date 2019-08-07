package org.aikidistas.currencyexchange.domain.ratedata;

import org.aikidistas.currencyexchange.domain.rate.Rate;
import org.junit.Test;

import java.util.Currency;

import static org.junit.Assert.assertEquals;

public class RatedCurrencyPairTest {

    private RatedCurrencyPair givenRatedCurrencyPair;
    private Currency actualMainCurrency;
    private Currency actualMoneyCurrency;

    @Test
    public void mainCurrency() {
        String expectedMainCurrency = "EUR";

        givenRatedCurrencyPair(expectedMainCurrency, "DKK", new Rate.Fake());
        whenMainCurrencyReceived();
        thenMainCurrencyResultEquals(expectedMainCurrency);
    }

    @Test
    public void moneyCurrency() {
        String expectedMoneyCurrency = "DKK";

        givenRatedCurrencyPair("EUR", expectedMoneyCurrency, new Rate.Fake());
        whenMoneyCurrencyReceived();
        thenMoneyCurrencyResultEquals(expectedMoneyCurrency);
    }

    @Test
    public void rate() {
        Rate expectedRate = new Rate.Dto(1);
        RatedCurrencyPair ratedCurrencyPair = new RatedCurrencyPair(Currency.getInstance("DKK"), Currency.getInstance("EUR"), new Rate.Fake(1));
        Rate actualRate = ratedCurrencyPair.rate();
        assertEquals(expectedRate, actualRate);
    }

    @Test
    public void testToString() {
        String expectedResult = "RatedCurrencyPair(mainCurrency=DKK, moneyCurrency=EUR, rate=Rate(amount=1))";
        assertEquals(expectedResult, new RatedCurrencyPair("DKK", "EUR", 1).toString());
    }

    private void givenRatedCurrencyPair(String expectedMainCurrency, String expectedMoneyCurrency, Rate expectedRate) {
        givenRatedCurrencyPair = new RatedCurrencyPair(expectedMainCurrency, expectedMoneyCurrency, expectedRate);
    }

    private void whenMainCurrencyReceived() {
        actualMainCurrency = givenRatedCurrencyPair.mainCurrency();
    }

    private void whenMoneyCurrencyReceived() {
        actualMoneyCurrency = givenRatedCurrencyPair.moneyCurrency();
    }

    private void thenMainCurrencyResultEquals(String expectedMainCurrency) {
        assertEquals(Currency.getInstance(expectedMainCurrency), actualMainCurrency);
    }

    private void thenMoneyCurrencyResultEquals(String expectedMoneyCurrency) {
        assertEquals(Currency.getInstance(expectedMoneyCurrency), actualMoneyCurrency);
    }
}