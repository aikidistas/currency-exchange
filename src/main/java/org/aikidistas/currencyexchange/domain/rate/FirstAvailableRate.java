package org.aikidistas.currencyexchange.domain.rate;

import com.jcabi.aspects.Immutable;
import org.aikidistas.currencyexchange.domain.exception.DomainException;
import org.aikidistas.currencyexchange.domain.exception.UnsupportedCurrencyPairException;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Immutable
public class FirstAvailableRate implements RateSource {
    private final RateSource[] rateSources;

    public FirstAvailableRate(RateSource... rateSources) {
        this.rateSources = rateSources;
    }

    @Override
    public Rate rate() throws DomainException {
        List<Rate> availableRates = availableRates();
        validateAvailableRates(availableRates);

        return availableRates.get(0);
    }

    private List<Rate> availableRates() {
        List<Rate> availableRates = new LinkedList<>();
        for (RateSource rateSource : rateSources) {
            availableRates.addAll(availableRate(rateSource));
        }
        return availableRates;
    }

    private void validateAvailableRates(List<Rate> availableRates) throws UnsupportedCurrencyPairException {
        if (availableRates.isEmpty()) {
            throw new UnsupportedCurrencyPairException();
        }
    }

    private List<Rate> availableRate(RateSource rateSource) {
        try {
            return rate(rateSource);
        } catch (DomainException e) {
            return noRate();
        }
    }

    private LinkedList<Rate> noRate() {
        return new LinkedList<>();
    }

    private List<Rate> rate(RateSource rateSource) throws DomainException {
        return Arrays.asList(rateSource.rate());
    }
}
