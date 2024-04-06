package com.stockman.service;

import com.baeldung.openapi.model.Position;
import com.stockman.FinnhubClient;
import com.stockman.entity.PortfolioItem;
import com.stockman.model.TradeRecommendation;
import com.stockman.repository.PortfolioRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class PortfolioService {

    private PortfolioRepository portfolioRepository;
    private FinnhubClient finnhubClient;
    public void addStockToPortfolio(Position position){
        try{
            portfolioRepository.save(PortfolioItem.builder()
                    .quantity(position.getQuantity()).price(position.getPrice()).symbol(position.getSymbol())
                    .portfolioId(position.getPortfolioId()).isBuy(position.getIsBuy()).build());
        }catch (Exception e){
            log.error("Something has gone wrong adding stock to portfolio");
        }
    }

    public BigDecimal getCurrentAveragePrice(String symbol, Integer portfolioId){
        PortfolioItem portfolioItem = portfolioRepository.findBySymbolAndPortfolioId(symbol, portfolioId);
        return portfolioItem.getPrice();
    }

    private Float calculateNewAverage(PortfolioItem existingStock, Integer quantity, BigDecimal price) {
        return null;
        //TODO logic to calcualte average
    }

    public List<TradeRecommendation> getTradeRecommendations(Integer portfolioId) {
        List<PortfolioItem> lastTradesForEachStock = portfolioRepository.findMostRecentTradesByPortfolioId(portfolioId);
        return lastTradesForEachStock.stream()
                .map(trade -> {
                    BigDecimal previousPrice = trade.getPrice();
                    BigDecimal currentPrice = finnhubClient.getStockPrice(trade.getSymbol());
                    TradeRecommendation.Action action = getAction(currentPrice, previousPrice, trade.getIsBuy());

                    return TradeRecommendation.builder()
                            .symbol(trade.getSymbol())
                            .currentPrice(currentPrice)
                            .action(action)
                            .build();
                })
                .toList();
    }

    private static TradeRecommendation.Action getAction(BigDecimal currentPrice, BigDecimal previousPrice, Boolean isBuy) {
        BigDecimal priceChangePercentage = currentPrice.subtract(previousPrice).divide(previousPrice, 2, BigDecimal.ROUND_HALF_UP)
                .multiply(BigDecimal.valueOf(100));

        TradeRecommendation.Action action;
        if (priceChangePercentage.compareTo(BigDecimal.valueOf(5)) >= 0) {
            action = TradeRecommendation.Action.SELL;
        } else if (priceChangePercentage.compareTo(BigDecimal.valueOf(-5)) <= 0) {
            action = TradeRecommendation.Action.BUY;
        } else {
            action = TradeRecommendation.Action.HOLD;
        }
        return action;
    }

}
