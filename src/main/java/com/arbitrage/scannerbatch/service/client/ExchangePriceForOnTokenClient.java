package com.arbitrage.scannerbatch.service.client;

import com.arbitrage.scannerbatch.service.client.dto.Ticker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;



@FeignClient(name= "exchangePriceForOnTokenClient", url = "https://api.coingecko.com/api/v3")
public interface ExchangePriceForOnTokenClient {
    @GetMapping("/coins/{token}/tickers")
    List<Ticker> getExchange(@PathVariable("token") String token);
}
