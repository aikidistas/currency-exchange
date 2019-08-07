package org.aikidistas.currencyexchange.domain.currency;

import org.aikidistas.currencyexchange.domain.exception.DomainException;

import java.util.Currency;

public interface CurrencySource {
    Currency currency() throws DomainException;

    class Fake implements CurrencySource {
        private final String currencyCode;

        public Fake(String currencyCode) {
            this.currencyCode = currencyCode;
        }

        @Override
        public Currency currency() {
            return Currency.getInstance(currencyCode);
        }
    }
}
