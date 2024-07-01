package com.service.shoping_cart_service.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ShopingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCart;
    @Column(name = "total_cost")
    private float totalCost;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> idProducts = new ArrayList<>();
}
