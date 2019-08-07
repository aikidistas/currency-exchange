package org.aikidistas.currencyexchange.domain.rate;

import com.jcabi.aspects.Immutable;
import org.aikidistas.currencyexchange.domain.currency.CurrencySource;
import org.aikidistas.currencyexchange.domain.exception.DomainException;
import org.aikidistas.currencyexchange.domain.exception.UnsupportedCurrencyPairException;
import org.aikidistas.currencyexchange.domain.ratedata.RateData;
import org.aikidistas.currencyexchange.domain.ratedata.RatedCurrencyPair;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.graph.DefaultDirectedGraph;

import java.util.Currency;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Immutable
public class IndirectRate implements RateSource {
    public static final int MAX_INTERMEDIATE_RATE_CONVERSIONS = 2;
    private final CurrencySource mainCurrency;
    private final CurrencySource moneyCurrency;
    private final RateData rateData;

    public IndirectRate(CurrencySource mainCurrency, CurrencySource moneyCurrency, RateData rateData) {
        this.mainCurrency = mainCurrency;
        this.moneyCurrency = moneyCurrency;
        this.rateData = rateData;
    }

    @Override
    public Rate rate() throws DomainException {
        List<GraphPath<Currency, RateEdge>> possiblePathList = graphPathsBetweenCurrencies(currencyRatesGraph());
        validatePaths(possiblePathList);

        return combinedRateFromFirstPath(possiblePathList);
    }

    private DefaultDirectedGraph<Currency, RateEdge> currencyRatesGraph() {
        DefaultDirectedGraph<Currency, RateEdge> graph = emptyGraph();
        addCurrenciesToGraph(graph);
        addRatesToGraph(graph);
        return graph;
    }

    private void validatePaths(List<GraphPath<Currency, RateEdge>> possiblePathList) throws UnsupportedCurrencyPairException {
        if (possiblePathList.isEmpty()) {
            throw new UnsupportedCurrencyPairException();
        }
    }

    private Rate combinedRateFromFirstPath(List<GraphPath<Currency, RateEdge>> possiblePathList) throws UnsupportedCurrencyPairException {
        int firstItemIndex = 0;
        return possiblePathList.get(firstItemIndex)
                .getEdgeList()
                .stream()
                .map(RateEdge::rate)
                .reduce(Rate::combinedRate)
                .orElseThrow(UnsupportedCurrencyPairException::new);
    }

    private List<GraphPath<Currency, RateEdge>> graphPathsBetweenCurrencies(DefaultDirectedGraph<Currency, RateEdge> graph) throws DomainException {
        try {
            return allDirectedPathsBetweenCurrencies(graph);
        } catch (IllegalArgumentException e) {
            throw new UnsupportedCurrencyPairException();
        }
    }

    private List<GraphPath<Currency, RateEdge>> allDirectedPathsBetweenCurrencies(DefaultDirectedGraph<Currency, RateEdge> graph) throws DomainException {
        return new AllDirectedPaths<>(graph).getAllPaths(
                mainCurrency.currency(),
                moneyCurrency.currency(),
                true,
                MAX_INTERMEDIATE_RATE_CONVERSIONS
        );
    }

    private DefaultDirectedGraph<Currency, RateEdge> emptyGraph() {
        return new DefaultDirectedGraph<>(RateEdge.class);
    }

    private void addCurrenciesToGraph(DefaultDirectedGraph<Currency, RateEdge> graph) {
        allCurrencies().forEach(graph::addVertex);
    }

    private Set<Currency> allCurrencies() {
        return rateData.ratedCurrencyPairs()
                .stream()
                .flatMap(currencyPair -> Stream.of(currencyPair.mainCurrency(), currencyPair.moneyCurrency()))
                .collect(Collectors.toSet());
    }

    private void addRatesToGraph(DefaultDirectedGraph<Currency, RateEdge> graph) {
        rateData.ratedCurrencyPairs().forEach(ratedCurrencyPair ->
                addEdgeToGivenGraph(graph, ratedCurrencyPair)
        );
    }

    private boolean addEdgeToGivenGraph(DefaultDirectedGraph<Currency, RateEdge> graph, RatedCurrencyPair ratedCurrencyPair) {
        return graph.addEdge(ratedCurrencyPair.mainCurrency(), ratedCurrencyPair.moneyCurrency(), new RateEdge(ratedCurrencyPair.rate()));
    }

    private static class RateEdge {
        private final Rate rate;

        public RateEdge(Rate rate) {
            this.rate = rate;
        }

        public Rate rate() {
            return rate;
        }
    }
}
