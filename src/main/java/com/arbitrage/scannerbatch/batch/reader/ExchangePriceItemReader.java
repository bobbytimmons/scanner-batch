package com.arbitrage.scannerbatch.batch.reader;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.arbitrage.scannerbatch.service.client.ExchangePriceForOnTokenClient;
import com.arbitrage.scannerbatch.service.client.dto.Ticker;

import java.util.Iterator;
import java.util.List;

@Component
@JobScope
@RequiredArgsConstructor
public class ExchangePriceItemReader implements ItemReader<Ticker> {

    private final ExchangePriceForOnTokenClient exchangePriceForOnTokenClient;

    private Iterator<Ticker> dataIterator;

    @Value("#{jobParameters['token']}")
    private String token;

    @Override
    public Ticker read(){
        if (dataIterator == null || !dataIterator.hasNext()) {
            List<Ticker> dataItems = exchangePriceForOnTokenClient.getExchange(token);
            dataIterator = dataItems.iterator();
        }
        return dataIterator.hasNext() ? dataIterator.next() : null;
    }
}

