package com.service.product_service.service;

import com.service.product_service.model.Products;

import java.util.List;

public interface IProductsService {

    public List<Products> getProducts();
    public Products getProductById(Long code);
    public Products saveProducts(Products products);
    public void deleteProducts(Long code);
}
