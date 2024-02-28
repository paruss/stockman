package com.stockman.scheduler;

import com.stockman.model.TradeRecommendation;
import com.stockman.service.PortfolioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class PortfolioAlertJob {

    private final PortfolioService portfolioService;

    public PortfolioAlertJob(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @Scheduled(fixedRate = 60000)
    public void processAlerts(){
        List<TradeRecommendation> tradeRecommendations = portfolioService.getTradeRecommendations();
        if(tradeRecommendations.isEmpty()){
            log.info("No Recommendations this time");
        }else{
            tradeRecommendations.forEach(tradeRecommendation -> log.info("Stock alert for {} suggestion is to {} the current price is {}", tradeRecommendation.getSymbol(),
                    tradeRecommendation.getAction(), tradeRecommendation.getCurrentPrice()));
        }
    }
}
