package org.aikidistas.currencyexchange.domain.rate;

import org.aikidistas.currencyexchange.domain.currency.CurrencySource;
import org.aikidistas.currencyexchange.domain.exception.DomainException;
import org.aikidistas.currencyexchange.domain.ratedata.RateData;

public class InvertedRate implements RateSource {
    private final ConstantRate originalRate;

    public InvertedRate(CurrencySource mainCurrency, CurrencySource moneyCurrency, RateData rateData) {
        this(new ConstantRate(mainCurrency, moneyCurrency, rateData));
    }

    public InvertedRate(ConstantRate originalRate) {
        this.originalRate = originalRate;
    }

    @Override
    public Rate rate() throws DomainException {
        return originalRate.rate().inverted();
    }
}
