package org.aikidistas.currencyexchange;

import com.jcabi.aspects.Immutable;
import org.aikidistas.currencyexchange.domain.currency.CurrencySource;
import org.aikidistas.currencyexchange.domain.exception.DomainException;
import org.aikidistas.currencyexchange.domain.exception.UnsupportedCurrencyPairException;
import org.aikidistas.currencyexchange.domain.moneyamount.ExchangedMoneyAmount;
import org.aikidistas.currencyexchange.domain.moneyamount.MoneyAmount;
import org.aikidistas.currencyexchange.domain.rate.*;
import org.aikidistas.currencyexchange.domain.ratedata.CombinedRateData;
import org.aikidistas.currencyexchange.domain.ratedata.InvertedRateData;
import org.aikidistas.currencyexchange.domain.ratedata.RateData;
import org.aikidistas.currencyexchange.infrastructure.CommandLineApplicationException;
import org.aikidistas.currencyexchange.infrastructure.Data;
import org.aikidistas.currencyexchange.infrastructure.Screen;

@Immutable
public class Application {
    private final Data data;
    private final Screen screen;

    public Application(Data data, Screen screen) {
        this.data = data;
        this.screen = screen;
    }

    public void run() {
        try {
            display(exchangedMoneyAmountSource().amount().textValue());
        } catch (NumberFormatException e) {
            display("<amount to exchange> is not valid. Valid example 1.23");
        } catch (CommandLineApplicationException e) {
            display("Usage: java -jar currencyexchange-1.0.jar <currency pair> <amount to exchange>");
        } catch (UnsupportedCurrencyPairException e) {
            display("<currency pair> is not supported. Valid example: EUR/DKK");
        } catch (DomainException e) {
            display("Sorry. We are experiencing an unknown application error. Please try again later");
        }
    }

    private MoneyAmount exchangedMoneyAmountSource() throws DomainException {
        RateData rateData = data.rateData();
        CurrencySource mainCurrency = data.mainCurrency();
        CurrencySource moneyCurrency = data.moneyCurrency();

        return new ExchangedMoneyAmount(
                data.moneyAmount(),
                firstAvailableRate(rateData, mainCurrency, moneyCurrency)
        ).moneyAmount();
    }

    private FirstAvailableRate firstAvailableRate(RateData rateData, CurrencySource mainCurrency, CurrencySource moneyCurrency) {
        return new FirstAvailableRate(
                new SameCurrencyRate(mainCurrency, moneyCurrency),
                new ConstantRate(mainCurrency, moneyCurrency, rateData),
                new InvertedRate(mainCurrency, moneyCurrency, rateData),
                indirectRate(rateData, mainCurrency, moneyCurrency)
        );
    }

    private IndirectRate indirectRate(RateData rateData, CurrencySource mainCurrency, CurrencySource moneyCurrency) {
        return new IndirectRate(
                mainCurrency,
                moneyCurrency,
                new CombinedRateData(rateData, new InvertedRateData(rateData))
        );
    }

    private void display(String text) {
        screen.show(text);
    }
}
