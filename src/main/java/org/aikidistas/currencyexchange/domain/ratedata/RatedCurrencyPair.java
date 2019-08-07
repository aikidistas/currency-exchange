package org.aikidistas.currencyexchange.domain.ratedata;

import com.jcabi.aspects.Immutable;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.aikidistas.currencyexchange.domain.rate.Rate;

import java.util.Currency;

@Immutable
@EqualsAndHashCode
@ToString
public class RatedCurrencyPair {
    private final Currency mainCurrency;
    private final Currency moneyCurrency;
    private final Rate rate;

    public RatedCurrencyPair(String mainCurrency, String moneyCurrency, double rate) {
        this(
                mainCurrency,
                moneyCurrency,
                new Rate.Dto(rate)
        );
    }

    public RatedCurrencyPair(String mainCurrency, String moneyCurrency, String rate) {
        this(
                mainCurrency,
                moneyCurrency,
                new Rate.Dto(rate)
        );
    }

    public RatedCurrencyPair(String mainCurrency, String moneyCurrency, Rate rate) {
        this(
                Currency.getInstance(mainCurrency),
                Currency.getInstance(moneyCurrency),
                rate
        );
    }

    public RatedCurrencyPair(Currency mainCurrency, Currency moneyCurrency, Rate rate) {
        this.mainCurrency = mainCurrency;
        this.moneyCurrency = moneyCurrency;
        this.rate = rate;
    }

    public Currency mainCurrency() {
        return mainCurrency;
    }

    public Currency moneyCurrency() {
        return moneyCurrency;
    }

    public Rate rate() {
        return rate;
    }
}
