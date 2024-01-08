package com.stockman.controller;

import com.baeldung.openapi.api.PositionsApi;
import com.baeldung.openapi.model.Position;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class PositionController implements PositionsApi {
    @Override
    public ResponseEntity<Position> addPosition(Position position) {
        return ResponseEntity.ok().body(position);
    }
}
