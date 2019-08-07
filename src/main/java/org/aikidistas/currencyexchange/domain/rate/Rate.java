package org.aikidistas.currencyexchange.domain.rate;

import com.jcabi.aspects.Immutable;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.aikidistas.currencyexchange.domain.amount.Amount;

@SuppressWarnings("squid:S1610")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public abstract class Rate {
    @EqualsAndHashCode.Include
    @ToString.Include
    public abstract Amount amount();

    public Rate combinedRate(Rate otherRate) {
        return new Rate.Dto(
                this.amount().multiplication(otherRate.amount())
        );
    }

    public Rate inverted() {
        return new Rate.Dto(
                new Amount(1).division(amount())
        );
    }

    @Immutable
    public static class Dto extends Rate {
        private final Amount amount;

        public Dto(int amount) {
            this(new Amount(amount));
        }

        public Dto(double amount) {
            this(new Amount(amount));
        }

        public Dto(String amount) {
            this(new Amount(amount));
        }

        public Dto(Amount amount) {
            this.amount = amount;
        }

        @Override
        public Amount amount() {
            return amount;
        }
    }

    @Immutable
    public static class Fake extends Rate {
        private final Amount rateAmount;

        public Fake() {
            this(1);
        }

        public Fake(int amount) {
            this(new Amount(amount));
        }

        public Fake(double amount) {
            this(new Amount(amount));
        }

        public Fake(Amount rateAmount) {
            this.rateAmount = rateAmount;
        }

        @Override
        public Amount amount() {
            return rateAmount;
        }
    }
}
