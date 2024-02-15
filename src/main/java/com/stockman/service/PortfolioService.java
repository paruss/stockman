package com.stockman.service;

import com.baeldung.openapi.model.Position;
import com.baeldung.openapi.model.Stock;
import com.stockman.entity.PortfolioItem;
import com.stockman.repository.PortfolioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PortfolioService {


    private PortfolioRepository portfolioRepository;
    public void addStockToPortfolio(Stock stock){
        PortfolioItem existingStock = portfolioRepository.findBySymbol(stock.getSymbol());
        if(existingStock == null){
            PortfolioItem portfolioItem = PortfolioItem.builder().symbol(stock.getSymbol()).build();
            portfolioRepository.save(portfolioItem);
        }
        log.info("Existing stock is " , existingStock.getSymbol());
    }
}
