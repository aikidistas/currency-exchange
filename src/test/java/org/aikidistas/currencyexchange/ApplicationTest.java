package org.aikidistas.currencyexchange;

import org.aikidistas.currencyexchange.domain.ratedata.DkkRateData;
import org.aikidistas.currencyexchange.domain.ratedata.RateData;
import org.aikidistas.currencyexchange.infrastructure.Data;
import org.aikidistas.currencyexchange.infrastructure.Screen;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class ApplicationTest {

    private Application application;
    private Screen.Fake screen;

    @Test
    public void applicationAcceptsDataAndScreen() {
        givenApplicationWithFakeScreenRateDataAndMultipleArguments(new RateData.Fake(), "EUR/DKK", "1");
    }

    @Test
    public void applicationRun() {
        givenApplicationWithFakeScreenRateDataAndMultipleArguments(new RateData.Fake(), "EUR/DKK", "1");
        whenApplicationRun();
    }

    @Test
    public void applicationRunAndShowResultInScreen() {
        givenApplicationWithFakeScreenRateDataAndMultipleArguments(new RateData.Fake(), "EUR/DKK", "1");
        whenApplicationRun();
        thenScreenOutputContains("1");
    }

    @Test
    public void applicationRunAndShowOtherResultInScreen() {
        givenApplicationWithFakeScreenRateDataAndMultipleArguments(new RateData.Fake(2), "EUR/DKK", "1");
        whenApplicationRun();
        thenScreenOutputContains("2");
    }

    @Test
    public void applicationRunAndShowAmountConvertedWithInvertedAndIndirectRateInScreen() {
        givenApplicationWithFakeScreenRateDataAndMultipleArguments(new DkkRateData(), "EUR/USD", "1");
        whenApplicationRun();
        thenScreenOutputContains("1.1219");
    }

    @Test
    public void givenInvalidAmount_whenAppRun_thenShowErrorMessage() {
        givenApplicationWithFakeScreenRateDataAndMultipleArguments(new DkkRateData(), "EUR/USD", "1,2.2,12.,2");
        whenApplicationRun();
        thenScreenOutputContains("<amount to exchange> is not valid. Valid example 1.23");
    }

    @Test
    public void givenNoArguments_whenAppRun_thenShowErrorMessage() {
        givenApplicationWithFakeScreenRateDataAndMultipleArguments(new DkkRateData());
        whenApplicationRun();
        thenScreenOutputContains("Usage: java -jar currencyexchange-1.0.jar <currency pair> <amount to exchange>");
    }

    @Test
    public void givenLessThanOneArgument_whenAppRun_thenShowErrorMessage() {
        givenApplicationWithFakeScreenRateDataAndMultipleArguments(new DkkRateData(), "DKK/EUR");
        whenApplicationRun();
        thenScreenOutputContains("Usage: java -jar currencyexchange-1.0.jar <currency pair> <amount to exchange>");
    }

    @Test
    public void givenMoreThanTwoArguments_whenAppRun_thenShowErrorMessage() {
        givenApplicationWithFakeScreenRateDataAndMultipleArguments(new DkkRateData(), "DKK/EUR", "someWrongArgument");
        whenApplicationRun();
        thenScreenOutputContains("<amount to exchange> is not valid. Valid example 1.23");
    }

    @Test
    public void givenInvalidCurrencyPair_whenAppRun_thenShowErrorMessage() {
        givenApplicationWithFakeScreenRateDataAndMultipleArguments(new DkkRateData(), "DKK/ASDjustTooLong", "1");
        whenApplicationRun();
        thenScreenOutputContains("<currency pair> is not supported. Valid example: EUR/DKK");
    }

    private void givenApplicationWithFakeScreenRateDataAndMultipleArguments(RateData rateData, String... commandLineArguments) {
        screen = new Screen.Fake();
        application = new Application(
                new Data(rateData, commandLineArguments),
                screen
        );
    }

    private void whenApplicationRun() {
        application.run();
    }

    private void thenScreenOutputContains(String expectedText) {
        assertThat(screen.output(), containsString(expectedText));
    }
}
