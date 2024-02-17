package com.stockman.controller;

import com.baeldung.openapi.api.StocksApi;
import com.baeldung.openapi.model.Position;
import com.baeldung.openapi.model.Stock;
import com.stockman.service.PortfolioService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class StocksController implements StocksApi {

    @Override
    public ResponseEntity<Stock> addStock(Stock stock) {
        return ResponseEntity.ok().body(stock);
    }
}
