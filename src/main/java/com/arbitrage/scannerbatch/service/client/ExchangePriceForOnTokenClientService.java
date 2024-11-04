package com.arbitrage.scannerbatch.service.client;

import com.arbitrage.scannerbatch.service.client.dto.Ticker;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Lazy
@Service
@AllArgsConstructor
public class ExchangePriceForOnTokenClientService {

    private final ExchangePriceForOnTokenClient client;

    public List<Ticker> getAdeptProject(String id) {
        return client.getExchange(id);
    }

}
