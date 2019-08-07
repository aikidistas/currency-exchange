package org.aikidistas.currencyexchange.domain.rate;

import com.jcabi.aspects.Immutable;
import org.aikidistas.currencyexchange.domain.currency.CurrencySource;
import org.aikidistas.currencyexchange.domain.exception.DomainException;
import org.aikidistas.currencyexchange.domain.exception.UnsupportedCurrencyPairException;
import org.aikidistas.currencyexchange.domain.ratedata.RateData;
import org.aikidistas.currencyexchange.domain.ratedata.RatedCurrencyPair;

import java.util.Currency;

@Immutable
public class ConstantRate implements RateSource {
    private final CurrencySource mainCurrencySource;
    private final CurrencySource moneyCurrencySource;
    private final RateData rateData;

    public ConstantRate(CurrencySource mainCurrencySource, CurrencySource moneyCurrencySource, RateData rateData) {
        this.mainCurrencySource = mainCurrencySource;
        this.moneyCurrencySource = moneyCurrencySource;
        this.rateData = rateData;
    }

    @Override
    public Rate rate() throws DomainException {
        return ratedCurrencyPair(mainCurrencySource.currency(), moneyCurrencySource.currency()).rate();
    }

    private RatedCurrencyPair ratedCurrencyPair(Currency mainCurrency, Currency moneyCurrency) throws UnsupportedCurrencyPairException {
        return rateData.ratedCurrencyPairs()
                .stream()
                .filter(ratedCurrencyPair -> mainCurrency.equals(ratedCurrencyPair.mainCurrency()) && moneyCurrency.equals(ratedCurrencyPair.moneyCurrency()))
                .findFirst()
                .orElseThrow(UnsupportedCurrencyPairException::new);
    }
}
