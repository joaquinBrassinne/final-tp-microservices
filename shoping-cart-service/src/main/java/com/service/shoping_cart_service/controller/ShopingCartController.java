package com.service.shoping_cart_service.controller;


import com.service.shoping_cart_service.dto.ShopingCartDTO;
import com.service.shoping_cart_service.model.ShopingCart;
import com.service.shoping_cart_service.service.IShopingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shoping-cart")
public class ShopingCartController {

    @Autowired
    IShopingCartService shopingCartService;

    @PostMapping("/add")
    public ResponseEntity<ShopingCart> addProductsToCart(@RequestBody ShopingCartDTO shoppingCartRequest) {
        ShopingCart shoppingCart = shopingCartService.saveShoppingCartWithProducts(shoppingCartRequest);
        return new ResponseEntity<>(shoppingCart, HttpStatus.CREATED);
    }

    @GetMapping
    public List<ShopingCart> getShopingCart(){
        return  this.shopingCartService.getShopingsCart();
    }

    @GetMapping("/{idCart}")
    public ShopingCart findCart(@PathVariable Long idCart){
        return this.shopingCartService.findShopingCart(idCart);
    }

    @PutMapping("/update/{idCart}")
    public ShopingCart editCartAndAddProducts(@RequestBody ShopingCartDTO shopingCartDTO, @PathVariable Long idCart){
        return this.shopingCartService.editShopingCartAndAddProducts(shopingCartDTO,idCart);
    }
}
