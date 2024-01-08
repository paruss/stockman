package com.stockman.controller;

import com.baeldung.openapi.api.StocksApi;
import com.baeldung.openapi.model.Stock;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class StocksController implements StocksApi {
    @Override
    public ResponseEntity<Stock> stocksPost(Stock stock) {
        return ResponseEntity.ok().body(stock);
    }
}
