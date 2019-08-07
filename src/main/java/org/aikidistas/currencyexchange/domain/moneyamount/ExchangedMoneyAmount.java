package org.aikidistas.currencyexchange.domain.moneyamount;

import com.jcabi.aspects.Immutable;
import org.aikidistas.currencyexchange.domain.exception.DomainException;
import org.aikidistas.currencyexchange.domain.rate.RateSource;

@Immutable
public class ExchangedMoneyAmount implements MoneyAmountSource {
    private final MoneyAmountSource moneyAmount;
    private final RateSource rate;

    public ExchangedMoneyAmount(MoneyAmountSource moneyAmount, RateSource rate) {
        this.moneyAmount = moneyAmount;
        this.rate = rate;
    }

    @Override
    public MoneyAmount moneyAmount() throws DomainException {
        return moneyAmount.moneyAmount().exchangedMoneyAmount(rate.rate());
    }
}
