package com.arbitrage.scannerbatch.service.client;

import com.arbitrage.scannerbatch.service.client.dto.CryptoData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name= "exchangePriceForOnTokenClient", url = "https://api.coingecko.com/api/v3")
public interface ExchangePriceForOnTokenClient {
    @GetMapping("/coins/{token}/tickers")
    CryptoData getExchange(@PathVariable("token") String token, @RequestHeader("apiKey") String apiKey);
}
