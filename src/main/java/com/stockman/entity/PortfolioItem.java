package com.stockman.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioItem {

    @Id
    @SequenceGenerator(name = "portfolio_item_seq", allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "portfolio_item_seq"
    )
    private Long id;
    private Integer portfolioId;

    private String symbol;
    private Integer quantity;
    private BigDecimal price;
    // Created timestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "purchase_date")
    private Date purchaseDate;
    private Boolean isBuy;

    @PrePersist
    protected void onCreate() {
        purchaseDate = new Date();
    }
}
