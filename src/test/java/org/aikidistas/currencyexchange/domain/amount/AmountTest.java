package org.aikidistas.currencyexchange.domain.amount;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AmountTest {

    private Amount amount;
    private Amount otherAmount;
    private String textValueResult;
    private Amount amountResult;

    @Test
    public void testAmountIntegerRepresentation() {
        givenAmountTextValue("1");
        whenTextValueIsCalculated();
        thenTextValueEquals("1");
    }

    @Test
    public void testAmountWithCents() {
        givenAmountTextValue("1.23456789");
        whenTextValueIsCalculated();
        thenTextValueEquals("1.23456789");
    }

    @Test
    public void multiplication() {
        givenAmountTextValue("3");
        whenMultiplicationInvokedWith("2");
        thenMultiplicationResultIsEquals("6");
    }

    @Test
    public void division() {
        givenAmountTextValue("6");
        whenDivisionInvokedWith("2");
        thenResultedAmountEquals("3");
    }

    @Test
    public void BigDecimalEquality() {
        givenAmountTextValue("10");
        givenOtherAmountTextValue("1E+1");

        thenTwoAmountsEqualByCompareToMethod();
        thenTwoAmountsEqualByEqualsMethod();
    }

    @Test
    public void scientificNotationTextValueIsReturnedAsNormalNumber() {
        givenAmountTextValue("1E+1");
        whenTextValueIsCalculated();
        thenTextValueEquals("10");
    }


    @Test
    public void divisionWithMaxScale_whenResultIsWithNonTerminatingDecimalPoints() {
        givenAmountTextValue("6.123456");
        whenDivisionInvokedWith("2.123456");
        thenResultedAmountEquals("2.8837");
    }

    @Test
    public void divisionWithMaxScale_roundLongerResultToFourDigitsScale() {
        givenAmountTextValue("0.22222");
        whenDivisionInvokedWith("2");
        thenResultedAmountEquals("0.1111");
    }

    private void givenAmountTextValue(String amountValue) {
        amount = new Amount(amountValue);
    }

    private void givenOtherAmountTextValue(String otherAmountTextValue) {
        otherAmount = new Amount(otherAmountTextValue);
    }

    private void whenTextValueIsCalculated() {
        textValueResult = amount.textValue();
    }

    private void whenMultiplicationInvokedWith(String amountToMultiply) {
        amountResult = this.amount.multiplication(new Amount(amountToMultiply));
    }

    private void whenDivisionInvokedWith(String divisor) {
        amountResult = this.amount.division(new Amount(divisor));
    }

    private void thenTextValueEquals(String expectedTextValue) {
        assertEquals(expectedTextValue, textValueResult);
    }

    private void thenMultiplicationResultIsEquals(String result) {
        assertEquals(new Amount(result), amountResult);
    }

    private void thenResultedAmountEquals(String result) {
        assertEquals(new Amount(result), amountResult);
    }

    private void thenTwoAmountsEqualByEqualsMethod() {
        assertEquals(amount, otherAmount);
    }

    private void thenTwoAmountsEqualByCompareToMethod() {
        assertTrue(amount.compareTo(otherAmount) == 0);
    }
}