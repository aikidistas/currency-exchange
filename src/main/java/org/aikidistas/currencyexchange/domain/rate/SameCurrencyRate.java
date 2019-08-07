package org.aikidistas.currencyexchange.domain.rate;

import com.jcabi.aspects.Immutable;
import org.aikidistas.currencyexchange.domain.currency.CurrencySource;
import org.aikidistas.currencyexchange.domain.exception.DomainException;
import org.aikidistas.currencyexchange.domain.exception.UnsupportedCurrencyPairException;

@Immutable
public class SameCurrencyRate implements RateSource {
    private final CurrencySource mainCurrency;
    private final CurrencySource moneyCurrency;

    public SameCurrencyRate(CurrencySource mainCurrency, CurrencySource moneyCurrency) {
        this.mainCurrency = mainCurrency;
        this.moneyCurrency = moneyCurrency;
    }

    @Override
    public Rate rate() throws DomainException {
        if (mainCurrency.currency().equals(moneyCurrency.currency())) {
            return new Rate.Dto(1);
        }

        throw new UnsupportedCurrencyPairException();
    }
}
