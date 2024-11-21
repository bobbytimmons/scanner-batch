package com.arbitrage.scannerbatch.service.client.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;



@Getter
@Setter
public class CryptoData {
    private String name;
    private List<Ticker> tickers;

}
