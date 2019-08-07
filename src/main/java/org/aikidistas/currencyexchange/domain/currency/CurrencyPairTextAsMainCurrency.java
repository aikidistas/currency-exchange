package org.aikidistas.currencyexchange.domain.currency;

import com.jcabi.aspects.Immutable;
import org.aikidistas.currencyexchange.domain.exception.DomainException;
import org.aikidistas.currencyexchange.domain.text.TextSource;
import org.aikidistas.currencyexchange.domain.text.ValidatedCurrencyPairText;

import java.util.Currency;

@Immutable
public class CurrencyPairTextAsMainCurrency implements CurrencySource {
    private final TextSource currencyPairTextSource;

    public CurrencyPairTextAsMainCurrency(TextSource currencyPairTextSource) {
        this.currencyPairTextSource = new ValidatedCurrencyPairText(currencyPairTextSource);
    }

    @Override
    public Currency currency() throws DomainException {
        return Currency.getInstance(extractedCurrencies(currencyPairTextSource.text())[0]);
    }

    private String[] extractedCurrencies(String isoCurrencyPairText) {
        return isoCurrencyPairText.split("/");
    }
}
