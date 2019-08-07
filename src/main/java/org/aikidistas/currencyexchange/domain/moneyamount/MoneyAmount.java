package org.aikidistas.currencyexchange.domain.moneyamount;

import com.jcabi.aspects.Immutable;
import lombok.EqualsAndHashCode;
import org.aikidistas.currencyexchange.domain.amount.Amount;
import org.aikidistas.currencyexchange.domain.rate.Rate;

@SuppressWarnings("squid:S1610")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class MoneyAmount {
    @EqualsAndHashCode.Include
    public abstract Amount amount();

    public MoneyAmount exchangedMoneyAmount(Rate rate) {
        return new MoneyAmount.Dto(this.amount().multiplication(rate.amount()));
    }

    @Immutable
    public static final class Dto extends MoneyAmount {
        private final Amount amount;

        public Dto(int number) {
            this(new Amount(number));
        }

        public Dto(String text) {
            this(new Amount(text));
        }

        public Dto(Amount amount) {
            this.amount = amount;
        }

        @Override
        public Amount amount() {
            return amount;
        }
    }

    public static final class Fake extends MoneyAmount {
        @Override
        public Amount amount() {
            return new Amount(1);
        }
    }
}
