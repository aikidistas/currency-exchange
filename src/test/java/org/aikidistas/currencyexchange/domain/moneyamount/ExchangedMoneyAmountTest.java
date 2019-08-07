package org.aikidistas.currencyexchange.domain.moneyamount;

import org.aikidistas.currencyexchange.domain.exception.DomainException;
import org.aikidistas.currencyexchange.domain.rate.RateSource;
import org.junit.Test;

public class ExchangedMoneyAmountTest {
    @Test
    public void exchangedMoneyAmount() {
        new ExchangedMoneyAmount(new MoneyAmountSource.Fake(), new RateSource.Fake());
    }

    @Test
    public void exchangedMoneyAmountReturnsMoneyAmount() throws DomainException {
        ExchangedMoneyAmount exchangedMoneyAmount = new ExchangedMoneyAmount(new MoneyAmountSource.Fake(), new RateSource.Fake());
        MoneyAmount moneyAmount = exchangedMoneyAmount.moneyAmount();
    }
}
