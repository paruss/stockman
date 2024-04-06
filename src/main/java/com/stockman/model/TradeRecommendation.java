package com.stockman.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class TradeRecommendation {
    private String symbol;
    private Action action;
    private BigDecimal currentPrice;

    public enum Action {
        BUY,
        SELL,
        HOLD
    }
}
