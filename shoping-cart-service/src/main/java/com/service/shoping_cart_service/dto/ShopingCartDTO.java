package com.service.shoping_cart_service.dto;

import lombok.*;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShopingCartDTO {
    private float totalCost;
    //private List<ProductsDTO> products;
    private List<Long> idProducts = new ArrayList<>();
}
