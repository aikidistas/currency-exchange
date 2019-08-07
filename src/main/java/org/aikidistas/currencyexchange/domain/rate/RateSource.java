package org.aikidistas.currencyexchange.domain.rate;

import com.jcabi.aspects.Immutable;
import org.aikidistas.currencyexchange.domain.exception.DomainException;
import org.aikidistas.currencyexchange.domain.exception.UnsupportedCurrencyPairException;

public interface RateSource {
    Rate rate() throws DomainException;

    @Immutable
    class Fake implements RateSource {
        private final Rate rate;

        public Fake() {
            this(new Rate.Fake(1));
        }

        public Fake(int rate) {
            this(new Rate.Fake(rate));
        }

        public Fake(Rate rate) {
            this.rate = rate;
        }

        @Override
        public Rate rate() {
            return rate;
        }
    }

    @Immutable
    class FakeUnavailable implements RateSource {
        @Override
        public Rate rate() throws UnsupportedCurrencyPairException {
            throw new UnsupportedCurrencyPairException();
        }
    }
}
