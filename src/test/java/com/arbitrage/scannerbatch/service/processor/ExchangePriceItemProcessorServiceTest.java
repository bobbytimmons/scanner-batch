package com.arbitrage.scannerbatch.service.processor;

import com.arbitrage.scannerbatch.service.client.dto.CryptoData;
import com.arbitrage.scannerbatch.service.client.dto.Ticker;
import com.arbitrage.scannerbatch.service.processor.dto.MaxGapTickers;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExchangePriceItemProcessorServiceTest {

    private final ExchangePriceItemProcessorService processorService = new ExchangePriceItemProcessorService();

    @Test
    void processReturnsMaxGapTickers() {
        Ticker ticker1 = new Ticker();
        ticker1.setBase("BTC");
        ticker1.setTarget("USD");
        ticker1.setLast(50000);

        Ticker ticker2 = new Ticker();
        ticker2.setBase("BTC");
        ticker2.setTarget("USD");
        ticker2.setLast(45000);

        Ticker ticker3 = new Ticker();
        ticker3.setBase("ETH");
        ticker3.setTarget("USD");
        ticker3.setLast(3000);

        Ticker ticker4 = new Ticker();
        ticker4.setBase("ETH");
        ticker4.setTarget("USD");
        ticker4.setLast(2500);

        List<Ticker> tickers = List.of(ticker1, ticker2, ticker3, ticker4);
        CryptoData cryptoData = new CryptoData();
        cryptoData.setTickers(tickers);

        MaxGapTickers result = processorService.process(cryptoData);

        assertEquals("BTC", result.getTicker1().getBase());
        assertEquals("BTC", result.getTicker2().getBase());
        assertEquals(5000, result.getGap());
    }

    @Test
    void processThrowsExceptionWhenNoDataFound() {
        Ticker ticker = new Ticker();
        ticker.setBase("BTC");
        ticker.setTarget("USD");
        ticker.setLast(50000);

        List<Ticker> tickers = List.of(ticker);
        CryptoData cryptoData = new CryptoData();
        cryptoData.setTickers(tickers);

        assertThrows(RuntimeException.class, () -> processorService.process(cryptoData));
    }






}
