package com.stockman.controller;

import com.baeldung.openapi.api.PositionPerformanceApi;
import com.baeldung.openapi.model.PositionPerformance;
import com.stockman.FinnhubClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;

@Controller
@AllArgsConstructor
public class PerformanceController implements PositionPerformanceApi {

    private FinnhubClient finnhubClient;

    @Override
    public ResponseEntity<PositionPerformance> positionPerformanceGet(String symbol) {
        BigDecimal result = finnhubClient.getStockPrice(symbol);
        PositionPerformance positionPerformance = new PositionPerformance();
        positionPerformance.setPerformance(result.toString());
        return ResponseEntity.ok(positionPerformance);
    }
}
