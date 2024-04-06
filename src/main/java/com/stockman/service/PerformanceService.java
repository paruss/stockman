package com.stockman.service;

import com.stockman.FinnhubClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@AllArgsConstructor
public class PerformanceService {

    private FinnhubClient finnhubClient;
    private PortfolioService portfolioService;

    public BigDecimal calculatePerformance(String symbol, Integer portfolioId) {
        BigDecimal currentPrice = finnhubClient.getStockPrice(symbol);
        BigDecimal averagePrice = portfolioService.getCurrentAveragePrice(symbol, portfolioId);
        BigDecimal difference = currentPrice.subtract(averagePrice);
        return difference.divide(averagePrice, 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100))
                .setScale(2, RoundingMode.HALF_UP);
    }
}
