package com.arbitrage.scannerbatch.service.processor;

import com.arbitrage.scannerbatch.service.client.dto.CryptoData;
import com.arbitrage.scannerbatch.service.client.dto.Ticker;
import com.arbitrage.scannerbatch.service.processor.dto.MaxGapTickers;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExchangePriceItemProcessorService {


    private static final String NO_DATA_FOUND = "No data found";


    public MaxGapTickers process(CryptoData dataItems) {
        return getMaxGap(filterSize(groupByBaseAndTarget(dataItems)));
    }

    private List<Ticker>  filterDecentralizedExchanges(List<Ticker> dataItems) {
        return dataItems;

    }

    private Map<String, List<Ticker>> groupByBaseAndTarget(CryptoData dataItems) {
        return dataItems.getTickers().stream()
                .collect(Collectors.groupingBy(Ticker::getBaseAndTargetConcatenetadeString));
    }

    private Map<String, List<Ticker>> filterSize(Map<String, List<Ticker>> dataItems) {
        return dataItems.entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


    private MaxGapTickers getMaxGap(Map<String, List<Ticker>> dataItems) {
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

    private Ticker getTickerWithMaxLast(List<Ticker> dataItems) {
        return dataItems.stream()
                .max(Comparator.comparing(Ticker::getLast))
                .orElseThrow(() -> new RuntimeException(NO_DATA_FOUND));
    }

    private Ticker getTickerWithMinLast(List<Ticker> dataItems) {
        return dataItems.stream()
                .min(Comparator.comparing(Ticker::getLast))
                .orElseThrow(() -> new RuntimeException(NO_DATA_FOUND));
    }

    private double getGap(Ticker maxLast, Ticker minLast) {
        return maxLast.getLast() - minLast.getLast();
    }
}
