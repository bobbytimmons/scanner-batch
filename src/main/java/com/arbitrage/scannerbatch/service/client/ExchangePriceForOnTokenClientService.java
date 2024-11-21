package com.arbitrage.scannerbatch.service.client;

import com.arbitrage.scannerbatch.service.client.dto.CryptoData;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Lazy
@Service
@AllArgsConstructor
public class ExchangePriceForOnTokenClientService {

    private final ExchangePriceForOnTokenClient client;

    public CryptoData getExchange(String id, String apiKey) {
        return client.getExchange(id, apiKey);
    }

}
