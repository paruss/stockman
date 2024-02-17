package com.stockman.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class PortfolioItem {

    @Id
    @SequenceGenerator(name = "portfolio_item_seq", allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "portfolio_item_seq"
    )
    private Long id;

    private String symbol;
    private Integer quantity;
    private Float averagePrice;
}
