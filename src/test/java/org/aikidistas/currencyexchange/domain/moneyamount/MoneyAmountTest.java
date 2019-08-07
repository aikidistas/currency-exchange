package org.aikidistas.currencyexchange.domain.moneyamount;

import org.aikidistas.currencyexchange.domain.rate.Rate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MoneyAmountTest {

    @Test
    public void exchangedMoneyAmount() {
        MoneyAmount resultedMoneyAmount = new MoneyAmount.Dto(1).exchangedMoneyAmount(new Rate.Dto(2));
        MoneyAmount expectedMoneyAmount = new MoneyAmount.Dto(2);
        assertEquals(expectedMoneyAmount, resultedMoneyAmount);
    }
}