package org.aikidistas.currencyexchange.domain.currency;

import org.aikidistas.currencyexchange.domain.exception.DomainException;
import org.aikidistas.currencyexchange.domain.text.TextSource;
import org.aikidistas.currencyexchange.domain.text.ValidatedCurrencyPairText;

import java.util.Currency;

public class CurrencyPairTextAsMoneyCurrency implements CurrencySource {

    private final TextSource currencyPairTextSource;

    public CurrencyPairTextAsMoneyCurrency(TextSource currencyPairTextSource) {
        this.currencyPairTextSource = new ValidatedCurrencyPairText(currencyPairTextSource);
    }

    @Override
    public Currency currency() throws DomainException {
        return Currency.getInstance(extractedCurrencies(currencyPairTextSource.text())[1]);
    }

    private String[] extractedCurrencies(String isoCurrencyPairText) {
        return isoCurrencyPairText.split("/");
    }
}
