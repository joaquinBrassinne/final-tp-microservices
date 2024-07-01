package com.service.product_service.controller;

import com.service.product_service.model.Products;
import com.service.product_service.service.IProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductsController {

    @Value("${server.port}")
    private int port;


    private final IProductsService productsService;

    @GetMapping("/get")
    public List<Products> getProducts(){
        System.out.println("----------------Estoy en el puerto -> " + port);
        return this.productsService.getProducts();
    }

    @GetMapping("/get/{code}")
    public Products getProductsByCode(@PathVariable Long code){
        System.out.println("----------------Estoy en el puerto -> " + port);
        return this.productsService.getProductById(code);
    }


    @PostMapping("/save")
    public Products saveProducts(@RequestBody Products products){
        return this.productsService.saveProducts(products);
    }

    @DeleteMapping("/delete/{code}")
    public String deleteProducts(@PathVariable Long code){
         this.productsService.deleteProducts(code);
         return "Deleted Product succesfully";
    }
}
