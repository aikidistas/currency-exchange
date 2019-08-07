package org.aikidistas.currencyexchange.domain.text;

import com.jcabi.aspects.Immutable;
import org.aikidistas.currencyexchange.domain.exception.DomainException;
import org.aikidistas.currencyexchange.domain.exception.UnsupportedCurrencyPairException;

@Immutable
public class ValidatedCurrencyPairText implements TextSource {
    private final TextSource currencyPairText;

    public ValidatedCurrencyPairText(TextSource currencyPairText) {
        this.currencyPairText = currencyPairText;
    }

    @Override
    public String text() throws DomainException {
        String text = currencyPairText.text();
        validate(text);
        return text;
    }

    private void validate(String text) throws DomainException {
        if (hasInCorrectLength(text)) {
            throw new UnsupportedCurrencyPairException();
        }

        if (dontHaveSlashAsSeparator(text)) {
            throw new UnsupportedCurrencyPairException();
        }
    }

    private boolean dontHaveSlashAsSeparator(String text) {
        return !"/".equals(text.substring(3, 4));
    }

    private boolean hasInCorrectLength(String text) {
        return text.length() != 7;
    }
}
