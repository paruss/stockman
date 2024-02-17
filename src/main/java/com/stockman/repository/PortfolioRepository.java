package com.stockman.repository;

import com.stockman.entity.PortfolioItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface PortfolioRepository extends CrudRepository<PortfolioItem, Long> {
     PortfolioItem findBySymbolAndPortfolioId(String symbol, BigDecimal portfolioId);
}
