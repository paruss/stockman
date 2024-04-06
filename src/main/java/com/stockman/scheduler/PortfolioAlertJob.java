package com.stockman.scheduler;

import com.stockman.model.TradeRecommendation;
import com.stockman.service.PortfolioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class PortfolioAlertJob {

    private final PortfolioService portfolioService;

    public PortfolioAlertJob(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @Scheduled(fixedRate = 60000)
    public void processAlerts(){
        Set<Integer> portfolioIds = getActivePortfolios();
        for (Integer portfolioId : portfolioIds) {
            List<TradeRecommendation> tradeRecommendations = portfolioService.getTradeRecommendations(portfolioId);
            if (tradeRecommendations.isEmpty()) {
                log.info("No Recommendations this time");
            } else {
                tradeRecommendations.forEach(tradeRecommendation -> log.info("Stock alert for {} suggestion is to {} " +
                                "the current price is {}", tradeRecommendation.getSymbol(),
                        tradeRecommendation.getAction(), tradeRecommendation.getCurrentPrice()));
            }
        }

    }

    private Set<Integer> getActivePortfolios() {
        HashSet<Integer> portfolios = new HashSet<>();
        portfolios.add(1);
        //TODO Find active portfolios.
        return  portfolios;
    }
}
