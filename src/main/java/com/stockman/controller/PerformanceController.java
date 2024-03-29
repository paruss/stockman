package com.stockman.controller;

import com.baeldung.openapi.api.PositionPerformanceApi;
import com.baeldung.openapi.model.PositionPerformance;
import com.stockman.service.PerformanceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;

@Controller
@AllArgsConstructor
public class PerformanceController implements PositionPerformanceApi {

    private PerformanceService performanceService;

    @Override
    public ResponseEntity<PositionPerformance> positionPerformanceGet(String symbol, Integer portfolioId) {
        BigDecimal result = performanceService.calculatePerformance(symbol, portfolioId);
        PositionPerformance positionPerformance = new PositionPerformance();
        positionPerformance.setPerformance(result);
        return ResponseEntity.ok(positionPerformance);
    }
}
