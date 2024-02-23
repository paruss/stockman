package com.stockman.repository;

import com.stockman.entity.PortfolioItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioRepository extends CrudRepository<PortfolioItem, Long> {
     PortfolioItem findBySymbolAndPortfolioId(String symbol, Integer portfolioId);
}
