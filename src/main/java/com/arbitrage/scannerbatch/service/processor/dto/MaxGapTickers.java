package com.arbitrage.scannerbatch.service.processor.dto;

import com.arbitrage.scannerbatch.service.client.dto.Ticker;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MaxGapTickers {

    Ticker ticker1;
    Ticker ticker2;
    double gap;
}
