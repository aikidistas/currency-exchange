package org.aikidistas.currencyexchange.domain.moneyamount;

import org.aikidistas.currencyexchange.domain.exception.DomainException;

public interface MoneyAmountSource {
    MoneyAmount moneyAmount() throws DomainException;

    class Fake implements MoneyAmountSource {
        @Override
        public MoneyAmount moneyAmount() {
            return new MoneyAmount.Fake();
        }
    }
}
