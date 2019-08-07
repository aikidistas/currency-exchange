package org.aikidistas.currencyexchange.infrastructure;

import com.jcabi.aspects.Immutable;
import org.aikidistas.currencyexchange.domain.currency.CurrencyPairTextAsMainCurrency;
import org.aikidistas.currencyexchange.domain.currency.CurrencyPairTextAsMoneyCurrency;
import org.aikidistas.currencyexchange.domain.currency.CurrencySource;
import org.aikidistas.currencyexchange.domain.moneyamount.MoneyAmountSource;
import org.aikidistas.currencyexchange.domain.moneyamount.TextAsMoneyAmount;
import org.aikidistas.currencyexchange.domain.ratedata.RateData;

@Immutable
public class Data {
    private final RateData rateData;
    private final CurrencySource mainCurrency;
    private final CurrencySource moneyCurrency;
    private final MoneyAmountSource moneyAmount;

    public Data(RateData rateData, String... commandLineArguments) {
        this(
                rateData,
                mainCurrency(commandLineArguments),
                moneyCurrency(commandLineArguments),
                moneyAmount(commandLineArguments)
        );
    }

    public Data(RateData rateData, CurrencySource mainCurrency, CurrencySource moneyCurrency, MoneyAmountSource moneyAmount) {
        this.rateData = rateData;
        this.mainCurrency = mainCurrency;
        this.moneyCurrency = moneyCurrency;
        this.moneyAmount = moneyAmount;
    }

    private static CurrencyPairTextAsMainCurrency mainCurrency(String[] commandLineArguments) {
        return new CurrencyPairTextAsMainCurrency(
                new CommandLineArgumentsAsIsoCurrencyPairText(commandLineArguments)
        );
    }

    private static CurrencyPairTextAsMoneyCurrency moneyCurrency(String[] commandLineArguments) {
        return new CurrencyPairTextAsMoneyCurrency(
                new CommandLineArgumentsAsIsoCurrencyPairText(commandLineArguments)
        );
    }

    private static TextAsMoneyAmount moneyAmount(String[] commandLineArguments) {
        return new TextAsMoneyAmount(new CommandLineArgumentsAsMoneyAmountText(commandLineArguments));
    }

    public CurrencySource mainCurrency() {
        return mainCurrency;
    }

    public CurrencySource moneyCurrency() {
        return moneyCurrency;
    }

    public MoneyAmountSource moneyAmount() {
        return moneyAmount;
    }

    public RateData rateData() {
        return rateData;
    }
}
