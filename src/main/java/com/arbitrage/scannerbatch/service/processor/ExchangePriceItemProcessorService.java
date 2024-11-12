package com.arbitrage.scannerbatch.service.processor;

import com.arbitrage.scannerbatch.service.client.dto.Ticker;
import com.arbitrage.scannerbatch.service.processor.dto.MaxGapTickers;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExchangePriceItemProcessorService {


    private static final String NO_DATA_FOUND = "No data found";

    public List<Ticker>  filterDecentralizedExchanges(List<Ticker> dataItems) {
        return dataItems;

    }

    public Map<String, List<Ticker>> groupByBaseAndTarget(List<Ticker> dataItems) {
        return dataItems.stream()
                .collect(Collectors.groupingBy(Ticker::getBaseAndTargetConcatenetadeString));
    }

    public Map<String, List<Ticker>> filterSize(Map<String, List<Ticker>> dataItems) {
        return dataItems.entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Ticker getTickerWithMaxLast(List<Ticker> dataItems) {
        return dataItems.stream()
                .max(Comparator.comparing(Ticker::getLast))
                .orElseThrow(() -> new RuntimeException(NO_DATA_FOUND));
    }

    public Ticker getTickerWithMinLast(List<Ticker> dataItems) {
        return dataItems.stream()
                .min(Comparator.comparing(Ticker::getLast))
                .orElseThrow(() -> new RuntimeException(NO_DATA_FOUND));
    }

    public double getGap(Ticker maxLast, Ticker minLast) {
        return maxLast.getLast() - minLast.getLast();
    }

    public MaxGapTickers getMaxGap(Map<String, List<Ticker>> dataItems) {
        return dataItems.values().stream()
                .map(tickers -> {
                    Ticker maxLast = getTickerWithMaxLast(tickers);
                    Ticker minLast = getTickerWithMinLast(tickers);
                    double gap = getGap(maxLast, minLast);
                    return MaxGapTickers.builder()
                            .ticker1(maxLast)
                            .ticker2(minLast)
                            .gap(gap)
                            .build();
                })
                .max(Comparator.comparing(MaxGapTickers::getGap))
                .orElseThrow(() -> new RuntimeException(NO_DATA_FOUND));
    }



}
