package com.service.sale_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleDTO {
    private Long idSale;
    private Long idCart;
    private LocalDate creationDate;
    private float totalCost;
}
