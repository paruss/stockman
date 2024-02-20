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
        PortfolioItem existingStock = portfolioRepository.findBySymbolAndPortfolioId(position.getSymbol(),
                position.getPortfolioId());
        if(existingStock == null){
            portfolioRepository.save(PortfolioItem.builder()
                    .quantity(position.getQuantity()).averagePrice(position.getPrice()).symbol(position.getSymbol())
                    .portfolioId(position.getPortfolioId()).build());
        }else{
            log.info("Existing stock is {}" , existingStock.getSymbol());
            Integer existingQuantity = existingStock.getQuantity();
            Float newAverage = calculateNewAverage(existingStock, position.getQuantity(), position.getPrice());

            existingStock.setQuantity(existingQuantity + position.getQuantity());
            portfolioRepository.save(existingStock);
        }

    }

    private Float calculateNewAverage(PortfolioItem existingStock, Integer quantity, Float price) {
        return null;
        //TODO logic to calcualte average
    }
}
