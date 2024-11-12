package com.arbitrage.scannerbatch.service.processor;

import com.arbitrage.scannerbatch.service.client.dto.Ticker;
import com.arbitrage.scannerbatch.service.processor.dto.MaxGapTickers;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ExchangePriceItemProcessorServiceTest {

    private final ExchangePriceItemProcessorService processorService = new ExchangePriceItemProcessorService();

    @Test
    void groupByBaseAndTarget_groupsCorrectly() {
        Ticker ticker1 = new Ticker();
        ticker1.setBase("BTC");
        ticker1.setTarget("USD");
        Ticker ticker2 = new Ticker();
        ticker2.setBase("BTC");
        ticker2.setTarget("EUR");
        Ticker ticker3 = new Ticker();
        ticker3.setBase("ETH");
        ticker3.setTarget("USD");
        Ticker ticker4 = new Ticker();
        ticker4.setBase("BTC");
        ticker4.setTarget("USD");
        List<Ticker> tickers = Arrays.asList(ticker1, ticker2, ticker3, ticker4);

        Map<String, List<Ticker>> result = processorService.groupByBaseAndTarget(tickers);

        assertEquals(3, result.size());
        assertEquals(2, result.get("BTCUSD").size());
    }

    @Test
    void groupByBaseAndTarget_emptyList() {
        List<Ticker> tickers = List.of();

        Map<String, List<Ticker>> result = processorService.groupByBaseAndTarget(tickers);

        assertTrue(result.isEmpty());
    }

    @Test
    void groupByBaseAndTarget_singleTicker() {
        Ticker ticker = new Ticker();
        ticker.setBase("BTC");
        ticker.setTarget("USD");
        List<Ticker> tickers = List.of(ticker);

        Map<String, List<Ticker>> result = processorService.groupByBaseAndTarget(tickers);

        assertEquals(1, result.size());
    }

    @Test
    void groupByBaseAndTarget_multipleGroups() {
        Ticker ticker1 = new Ticker();
        ticker1.setBase("BTC");
        ticker1.setTarget("USD");
        Ticker ticker2 = new Ticker();
        ticker2.setBase("BTC");
        ticker2.setTarget("USD");
        Ticker ticker3 = new Ticker();
        ticker3.setBase("ETH");
        ticker3.setTarget("USD");
        Ticker ticker4 = new Ticker();
        ticker4.setBase("ETH");
        ticker4.setTarget("USD");
        List<Ticker> tickers = Arrays.asList(ticker1, ticker2, ticker3, ticker4);

        Map<String, List<Ticker>> result = processorService.groupByBaseAndTarget(tickers);

        assertEquals(2, result.size());
        assertEquals(2, result.get("BTCUSD").size());
        assertEquals(2, result.get("ETHUSD").size());
    }

    @Test
    void filterSize_removesSingleElementGroups() {
        Ticker ticker1 = new Ticker();
        ticker1.setBase("BTC");
        ticker1.setTarget("USD");
        Ticker ticker2 = new Ticker();
        ticker2.setBase("BTC");
        ticker2.setTarget("USD");
        Ticker ticker3 = new Ticker();
        ticker3.setBase("ETH");
        ticker3.setTarget("USD");
        List<Ticker> tickers = Arrays.asList(ticker1, ticker2, ticker3);

        Map<String, List<Ticker>> grouped = processorService.groupByBaseAndTarget(tickers);
        Map<String, List<Ticker>> filtered = processorService.filterSize(grouped);

        assertEquals(1, filtered.size());
        assertEquals(2, filtered.get("BTCUSD").size());
    }

    @Test
    void filterSize_keepsMultipleElementGroups() {
        Ticker ticker1 = new Ticker();
        ticker1.setBase("BTC");
        ticker1.setTarget("USD");
        Ticker ticker2 = new Ticker();
        ticker2.setBase("BTC");
        ticker2.setTarget("USD");
        Ticker ticker3 = new Ticker();
        ticker3.setBase("ETH");
        ticker3.setTarget("USD");
        Ticker ticker4 = new Ticker();
        ticker4.setBase("ETH");
        ticker4.setTarget("USD");
        List<Ticker> tickers = Arrays.asList(ticker1, ticker2, ticker3, ticker4);

        Map<String, List<Ticker>> grouped = processorService.groupByBaseAndTarget(tickers);
        Map<String, List<Ticker>> filtered = processorService.filterSize(grouped);

        assertEquals(2, filtered.size());
        assertEquals(2, filtered.get("BTCUSD").size());
        assertEquals(2, filtered.get("ETHUSD").size());
    }

    @Test
    void filterSize_emptyMap() {
        Map<String, List<Ticker>> grouped = Map.of();

        Map<String, List<Ticker>> filtered = processorService.filterSize(grouped);

        assertTrue(filtered.isEmpty());
    }

    @Test
    void filterSize_allSingleElementGroups() {
        Ticker ticker1 = new Ticker();
        ticker1.setBase("BTC");
        ticker1.setTarget("USD");
        Ticker ticker2 = new Ticker();
        ticker2.setBase("ETH");
        ticker2.setTarget("USD");
        List<Ticker> tickers = Arrays.asList(ticker1, ticker2);

        Map<String, List<Ticker>> grouped = processorService.groupByBaseAndTarget(tickers);
        Map<String, List<Ticker>> filtered = processorService.filterSize(grouped);

        assertTrue(filtered.isEmpty());
    }

    @Test
    void getTickerWithMaxVolume_returnsTickerWithHighestLast() {
        Ticker ticker1 = new Ticker();
        ticker1.setLast(100.0);
        Ticker ticker2 = new Ticker();
        ticker2.setLast(200.0);
        Ticker ticker3 = new Ticker();
        ticker3.setLast(150.0);
        List<Ticker> tickers = Arrays.asList(ticker1, ticker2, ticker3);

        Ticker result = processorService.getTickerWithMaxLast(tickers);

        assertEquals(200.0, result.getLast());
    }

    @Test
    void getTickerWithMinVolume_returnsTickerWithLowestLast() {
        Ticker ticker1 = new Ticker();
        ticker1.setLast(100.0);
        Ticker ticker2 = new Ticker();
        ticker2.setLast(200.0);
        Ticker ticker3 = new Ticker();
        ticker3.setLast(150.0);
        List<Ticker> tickers = Arrays.asList(ticker1, ticker2, ticker3);

        Ticker result = processorService.getTickerWithMinLast(tickers);

        assertEquals(100.0, result.getLast());
    }

    @Test
    void getTickerWithMinVolume_singleTicker() {
        Ticker ticker = new Ticker();
        ticker.setLast(100.0);
        List<Ticker> tickers = List.of(ticker);

        Ticker result = processorService.getTickerWithMinLast(tickers);

        assertEquals(100.0, result.getLast());
    }

    @Test
    void getTickerWithMinLast_throwsExceptionForEmptyList() {
        List<Ticker> tickers = List.of();

        Exception exception = assertThrows(RuntimeException.class, () -> processorService.getTickerWithMinLast(tickers));

        assertEquals("No data found", exception.getMessage());
    }

    @Test
    void getMaxGap_emptyMap() {
        Map<String, List<Ticker>> grouped = Map.of();

        Exception exception = assertThrows(RuntimeException.class, () -> processorService.getMaxGap(grouped));


        assertEquals("No data found", exception.getMessage());
    }

    @Test
    void getMaxGap_singleGroup() {
        Ticker ticker1 = new Ticker();
        ticker1.setLast(100.0);
        Ticker ticker2 = new Ticker();
        ticker2.setLast(200.0);
        Map<String, List<Ticker>> grouped = Map.of("BTCUSD", Arrays.asList(ticker1, ticker2));

        MaxGapTickers result = processorService.getMaxGap(grouped);

        assertEquals(200.0, result.getTicker1().getLast());
        assertEquals(100.0, result.getTicker2().getLast());
        assertEquals(100.0, result.getGap());
    }

    @Test
    void getMaxGap_multipleGroupsWithSameGap() {
        Ticker ticker1 = new Ticker();
        ticker1.setLast(100.0);
        Ticker ticker2 = new Ticker();
        ticker2.setLast(200.0);
        Ticker ticker3 = new Ticker();
        ticker3.setLast(149.0);
        Ticker ticker4 = new Ticker();
        ticker4.setLast(50.0);
        Map<String, List<Ticker>> grouped = Map.of(
                "BTCUSD", Arrays.asList(ticker1, ticker2),
                "ETHUSD", Arrays.asList(ticker3, ticker4)
        );

        MaxGapTickers result = processorService.getMaxGap(grouped);

        assertEquals(200.0, result.getTicker1().getLast());
        assertEquals(100.0, result.getTicker2().getLast());
        assertEquals(100.0, result.getGap());
    }
}
