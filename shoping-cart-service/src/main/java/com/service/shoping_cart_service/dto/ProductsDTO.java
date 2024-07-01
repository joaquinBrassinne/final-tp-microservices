package com.service.shoping_cart_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductsDTO {
    private Long code;
    private String name;
    private String brand;
    private float price;
}
