package com.arbitrage.scannerbatch.service.client.dto;

import java.util.Date;

public class Ticker {
    private String base;
    private String target;
    private Market market;
    private double last;
    private double volume;
    private ConvertedLast convertedLast;
    private ConvertedVolume convertedVolume;
    private String trustScore;
    private double bidAskSpreadPercentage;
    private Date timestamp;
    private Date lastTradedAt;
    private Date lastFetchAt;
    private boolean isAnomaly;
    private boolean isStale;
    private String tradeUrl;
    private String tokenInfoUrl;
    private String coinId;
    private String targetCoinId;

    // Getters and Setters
}
