package org.aikidistas.currencyexchange.domain.amount;

import com.jcabi.aspects.Immutable;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * Amount usage in domain:
 * 1. Amount of "Money"
 * 2. "Exchange Rate" Amount
 */
@Immutable
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Amount implements Comparable<Amount> {
    private static final int MAX_SCALE = 4;
    private final BigDecimal bigDecimalValue;

    public Amount(int amount) {
        this(new BigDecimal(amount));
    }

    public Amount(double amount) {
        this(BigDecimal.valueOf(amount));
    }

    public Amount(String amount) {
        this(new BigDecimal(amount));
    }

    public Amount(BigDecimal amount) {
        this.bigDecimalValue = bigDecimalWithoutZeroes(amount);
    }

    @EqualsAndHashCode.Include
    public String textValue() {
        return bigDecimalValue.toPlainString();
    }

    public Amount multiplication(Amount factor) {
        return new Amount(multipliedBigDecimalWithMaxScale(factor));
    }

    private BigDecimal multipliedBigDecimalWithMaxScale(Amount factor) {
        return roundedBigDecimal(
                bigDecimalValue.multiply(factor.bigDecimalValue())
        );
    }

    public Amount division(Amount divisor) {
        return new Amount(dividedBigDecimalWithMaxScale(divisor.bigDecimalValue()));
    }

    private BigDecimal dividedBigDecimalWithMaxScale(BigDecimal divisor) {
        return bigDecimalValue.divide(divisor, MAX_SCALE, BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal roundedBigDecimal(BigDecimal dividedAmount) {
        if (isScaleBiggerThanMax(dividedAmount)) {
            dividedAmount = amountWithMaxScale(dividedAmount);
        }
        return dividedAmount;
    }

    private BigDecimal amountWithMaxScale(BigDecimal dividedAmount) {
        return dividedAmount.setScale(MAX_SCALE, BigDecimal.ROUND_HALF_UP);
    }

    private boolean isScaleBiggerThanMax(BigDecimal dividedAmount) {
        return dividedAmount.scale() > MAX_SCALE;
    }

    private BigDecimal bigDecimalWithoutZeroes(BigDecimal bigDecimal) {
        return bigDecimal.stripTrailingZeros();
    }

    private BigDecimal bigDecimalValue() {
        return bigDecimalValue;
    }

    public String toString() {
        return textValue();
    }

    @Override
    public int compareTo(Amount other) {
        return this.bigDecimalValue.compareTo(other.bigDecimalValue);
    }
}
