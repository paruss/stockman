package com.stockman.repository;

import com.stockman.entity.PortfolioItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortfolioRepository extends CrudRepository<PortfolioItem, Long> {
     PortfolioItem findBySymbolAndPortfolioId(String symbol, Integer portfolioId);

     @Query("SELECT pi FROM PortfolioItem pi WHERE pi.portfolioId = ?1 AND (pi.symbol, pi.purchaseDate) IN " +
             "(SELECT p.symbol, MAX(p.purchaseDate) FROM PortfolioItem p WHERE p.portfolioId = ?1 GROUP BY p.symbol)")
     List<PortfolioItem> findMostRecentTradesByPortfolioId(Integer portfolioId);
}
