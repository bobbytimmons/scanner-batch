package com.arbitrage.scannerbatch.batch.processor;

import com.arbitrage.scannerbatch.service.client.dto.Ticker;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExchangePriceItemProcessor implements ItemProcessor<List<Ticker>, List<Ticker>> {

    @Override
    public List<Ticker> process(List<Ticker> dataItems)  {

        return dataItems; // Return the processed list
    }
}
