package com.arbitrage.scannerbatch.batch.processor;

import com.arbitrage.scannerbatch.service.client.dto.CryptoData;
import com.arbitrage.scannerbatch.service.processor.ExchangePriceItemProcessorService;
import com.arbitrage.scannerbatch.service.processor.dto.MaxGapTickers;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExchangePriceItemProcessor implements ItemProcessor< CryptoData,MaxGapTickers> {

    private final ExchangePriceItemProcessorService exchangePriceItemProcessorService;


    @Override
    public MaxGapTickers process(@NonNull CryptoData dataItems)  {
        return exchangePriceItemProcessorService.process(dataItems); // Return the processed list
    }

}
