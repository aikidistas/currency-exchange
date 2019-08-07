package org.aikidistas.currencyexchange.domain.ratedata;

import com.jcabi.aspects.Immutable;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Immutable
public class CombinedRateData implements RateData {
    private final List<RateData> rateDataList;

    public CombinedRateData(RateData... rateDataList) {
        this(Arrays.asList(rateDataList));
    }

    public CombinedRateData(List<RateData> rateDataList) {
        this.rateDataList = rateDataList;
    }

    @Override
    public Set<RatedCurrencyPair> ratedCurrencyPairs() {
        return rateDataList
                .stream()
                .flatMap(this::rateDataToStreamOfRatedCurrencyPairs)
                .collect(Collectors.toSet());
    }

    private Stream<? extends RatedCurrencyPair> rateDataToStreamOfRatedCurrencyPairs(RateData rateData) {
        Set<RatedCurrencyPair> pairs = rateData.ratedCurrencyPairs();

        return Stream.of(pairs.toArray(new RatedCurrencyPair[pairs.size()]));
    }
}
