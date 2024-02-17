package com.stockman.controller;

import com.baeldung.openapi.api.PositionsApi;
import com.baeldung.openapi.model.Position;
import com.stockman.service.PortfolioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class PositionController implements PositionsApi {

    private PortfolioService portfolioService;

    @Override
    public ResponseEntity<Position> addPosition(Position position) {
        portfolioService.addStockToPortfolio(position);
        return ResponseEntity.ok().body(position);
    }
}
