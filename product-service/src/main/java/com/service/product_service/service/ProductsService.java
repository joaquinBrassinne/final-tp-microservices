package com.service.product_service.service;

import com.service.product_service.model.Products;
import com.service.product_service.repository.IProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductsService implements IProductsService{

    private final IProductsRepository productsRepository;


    @Override
    public List<Products> getProducts() {
        return this.productsRepository.findAll();
    }

    @Override
    public Products getProductById(Long code) {
        Products products = this.productsRepository.findById(code)
                .orElseThrow(()-> new RuntimeException("Product with that code doesnt exist"));

        return products;
    }



    @Override
    public Products saveProducts(Products products) {
        return this.productsRepository.save(products);
    }

    @Override
    public void deleteProducts(Long code) {
        Products productsDelete = this.getProductById(code);
        this.productsRepository.delete(productsDelete);
    }


}
