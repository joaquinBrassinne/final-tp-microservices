package com.service.shoping_cart_service.repository;

import com.service.shoping_cart_service.dto.ProductsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "product-service")
public interface IProductsAPI {

    @GetMapping("/products/get")
    public List<ProductsDTO> getProducts();

    @GetMapping("/products/get/{code}")
    public ProductsDTO getProduct(@PathVariable("code") Long code);
}
