package com.stockman.service;

import com.stockman.FinnhubClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PerformanceServiceTest {

    private static final String SYMBOL = "AAPL";
    private static final Integer PORTFOLIO_ID = 1;
    private static final BigDecimal VALID_RESULT = new BigDecimal("7.69");
    private static final BigDecimal STOCK_PRICE = new BigDecimal(140);
    private static final BigDecimal CURRENT_AVERAGE = new BigDecimal(130);
    @Autowired
    private PerformanceService performanceService;

    @MockBean
    private FinnhubClient finnhubClient;

    @MockBean
    private PortfolioService portfolioService;

    @BeforeEach
    void setup(){
        when(finnhubClient.getStockPrice(SYMBOL)).thenReturn(STOCK_PRICE);
        when(portfolioService.getCurrentAveragePrice(SYMBOL, 1)).thenReturn(CURRENT_AVERAGE);
    }


    @Test
    void givenValidDataProvided_WhenCalculatePerformance_ThenValidPerformanceReturned() {
        BigDecimal result = performanceService.calculatePerformance(SYMBOL, PORTFOLIO_ID);
        assertThat(result).isEqualTo(VALID_RESULT);
    }
}