package com.service.sale_service.repository;

import com.service.sale_service.dto.ShopingCartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "shoping-cart")
public interface IShopingCartAPI {

    @GetMapping("/shoping-cart/{idCart}")
    public ShopingCartDTO findShopingCart(@PathVariable Long idCart);
}
