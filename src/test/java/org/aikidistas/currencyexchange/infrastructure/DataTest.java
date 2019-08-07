package org.aikidistas.currencyexchange.infrastructure;

import org.aikidistas.currencyexchange.domain.currency.CurrencyPairTextAsMainCurrency;
import org.aikidistas.currencyexchange.domain.currency.CurrencyPairTextAsMoneyCurrency;
import org.aikidistas.currencyexchange.domain.currency.CurrencySource;
import org.aikidistas.currencyexchange.domain.moneyamount.MoneyAmountSource;
import org.aikidistas.currencyexchange.domain.moneyamount.TextAsMoneyAmount;
import org.aikidistas.currencyexchange.domain.ratedata.DkkRateData;
import org.aikidistas.currencyexchange.domain.ratedata.RateData;
import org.aikidistas.currencyexchange.domain.text.Text;
import org.junit.Test;

public class DataTest {
    @Test
    public void acceptsCommandLineArguments() {
        new Data(new RateData.Fake(), "EUR/DKK", "1");
    }

    @Test
    public void returnsMainCurrency() {
        Data data = new Data(new RateData.Fake(), "EUR/DKK", "1");
        CurrencySource mainCurrency = data.mainCurrency();
    }

    @Test
    public void returnsMoneyCurrency() {
        Data data = new Data(new RateData.Fake(), "EUR/DKK", "1");
        CurrencySource moneyCurrency = data.moneyCurrency();
    }

    @Test
    public void returnsMoneyAmount() {
        Data data = new Data(new RateData.Fake(), "EUR/DKK", "1");
        MoneyAmountSource moneyAmount = data.moneyAmount();
    }

    @Test
    public void returnsRateData() {
        Data data = new Data(new RateData.Fake(), "EUR/DKK", "1");
        RateData rateData = data.rateData();
    }

    @Test
    public void acceptsAllArgumentsExplicitly() {
        new Data(
                new RateData.Fake(),
                new CurrencySource.Fake("EUR"),
                new CurrencySource.Fake("DKK"),
                new MoneyAmountSource.Fake()
        );
    }

    @Test
    public void name() {
        Data data = new Data(
                new DkkRateData(),
                new CurrencyPairTextAsMainCurrency(new Text("EUR/DKK")),
                new CurrencyPairTextAsMoneyCurrency(new Text("EUR/DKK")),
                new TextAsMoneyAmount("1")
        );
    }
}
