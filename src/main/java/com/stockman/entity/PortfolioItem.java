package com.stockman.entity;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class PortfolioItem {

    private Long id;

    private String symbol;
    private Integer quantity;
    private Integer averagePrice;


}
