package org.aikidistas.currencyexchange.domain.moneyamount;

import org.aikidistas.currencyexchange.domain.exception.DomainException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TextAsMoneyAmountTest {
    @Test
    public void textAsMoneyAmount() {
        TextAsMoneyAmount textAsMoneyAmount = new TextAsMoneyAmount("1");
    }

    @Test
    public void textAsMoneyAmountReturnsMoneyAmount() throws DomainException {
        TextAsMoneyAmount textAsMoneyAmount = new TextAsMoneyAmount("1");
        MoneyAmount moneyAmount = textAsMoneyAmount.moneyAmount();
    }

    @Test
    public void moneyAmountToString() throws DomainException {
        TextAsMoneyAmount textAsMoneyAmount = new TextAsMoneyAmount("1");
        MoneyAmount moneyAmount = textAsMoneyAmount.moneyAmount();
        assertEquals("1", moneyAmount.amount().textValue());
    }
}
