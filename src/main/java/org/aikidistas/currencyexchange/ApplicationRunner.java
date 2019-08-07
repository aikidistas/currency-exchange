package org.aikidistas.currencyexchange;

import org.aikidistas.currencyexchange.domain.ratedata.RateDataForHundred;
import org.aikidistas.currencyexchange.domain.ratedata.RatedCurrencyPair;
import org.aikidistas.currencyexchange.infrastructure.ConsoleScreen;
import org.aikidistas.currencyexchange.infrastructure.Data;

public class ApplicationRunner {
    public static void main(String... args) {
        Application app = new Application(data(args), new ConsoleScreen());
        app.run();
    }

    private static Data data(String[] args) {
        return new Data(
                hardcodedRateDataForHundredAmount(),
                args
        );
    }

    private static RateDataForHundred hardcodedRateDataForHundredAmount() {
        return new RateDataForHundred(
                new RatedCurrencyPair("EUR", "DKK", 743.94),
                new RatedCurrencyPair("USD", "DKK", 663.11),
                new RatedCurrencyPair("GBP", "DKK", 852.85),
                new RatedCurrencyPair("SEK", "DKK", 76.10),
                new RatedCurrencyPair("NOK", "DKK", 78.40),
                new RatedCurrencyPair("CHF", "DKK", 683.58),
                new RatedCurrencyPair("JPY", "DKK", 5.9740)

        );
    }
}
