package com.stockman.service;

import com.baeldung.openapi.model.Position;
import com.stockman.entity.PortfolioItem;
import com.stockman.repository.PortfolioRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class PortfolioService {

    private PortfolioRepository portfolioRepository;
    public void addStockToPortfolio(Position position){
        PortfolioItem existingStock = portfolioRepository.findBySymbolAndPortfolioId(position.getSymbol(), position.getPortfolioId());
        if(existingStock == null){
            portfolioRepository.save(PortfolioItem.builder()
                    .quantity(position.getQuantity()).averagePrice(position.getPrice()).symbol(position.getSymbol()).portfolioId(position.getPortfolioId()).build());
        }
        log.info("Existing stock is " , existingStock.getSymbol());
    }
}
