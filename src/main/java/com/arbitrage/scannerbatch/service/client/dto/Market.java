package com.arbitrage.scannerbatch.service.client.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Market {
    private String name;
    private String identifier;
    private boolean hasTradingIncentive;

    // Getters and Setters
}
