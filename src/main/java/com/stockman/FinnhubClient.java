package com.stockman;

import com.stockman.model.StockPrice;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class FinnhubClient {

    private RestTemplate restTemplate;

    public BigDecimal getStockPrice(String symbol){
        ResponseEntity<StockPrice> result = restTemplate.getForEntity
                ("https://finnhub.io/api/v1/quote?symbol=AAPL&token=YOUR_TOKEN_HERE", StockPrice.class);
        return result.getBody().getStockPrice();
    }
}
