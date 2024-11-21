package com.arbitrage.scannerbatch.batch.reader;

import com.arbitrage.scannerbatch.service.client.ExchangePriceForOnTokenClientService;
import com.arbitrage.scannerbatch.service.client.dto.CryptoData;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



@Component
@JobScope
@RequiredArgsConstructor
public class ExchangePriceItemReader implements ItemReader<CryptoData> {

    private final ExchangePriceForOnTokenClientService exchangePriceForOnTokenClientService;


    @Value("#{jobParameters['token']}")
    private String token;

    @Value("#{jobParameters['apiKey']}")
    private String apiKey;

    @Override
    public CryptoData read() throws InterruptedException {
        // Introduce a delay between API calls
        long delay = 2500;
        Thread.sleep(delay);
       System.out.println("reading data from exchangePriceForOnTokenClientService.getExchange(token)");
           return exchangePriceForOnTokenClientService.getExchange(token, apiKey);
    }
}

