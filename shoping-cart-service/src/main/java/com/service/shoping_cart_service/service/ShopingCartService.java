package com.service.shoping_cart_service.service;

import com.service.shoping_cart_service.dto.ProductsDTO;
import com.service.shoping_cart_service.dto.ShopingCartDTO;
import com.service.shoping_cart_service.model.ShopingCart;
import com.service.shoping_cart_service.repository.IProductsAPI;
import com.service.shoping_cart_service.repository.IShopingCartRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class ShopingCartService implements IShopingCartService {

    @Autowired
    IShopingCartRepository shopingCartRepository;

    @Autowired
    IProductsAPI productsAPI;

    @Override
    public List<ShopingCart> getShopingsCart() {
        return shopingCartRepository.findAll();
    }

    @Override
    public ShopingCart findShopingCart(Long id) {
        return shopingCartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    @Override
    @CircuitBreaker(name = "product-service", fallbackMethod = "fallbackGetShopingCart")
    @Retry(name = "product-service")
    public ShopingCart saveShoppingCartWithProducts(ShopingCartDTO shoppingCartRequest) {

        ShopingCart shoppingCart = new ShopingCart();
        float totalCost = 0;

        for (Long codeProduct : shoppingCartRequest.getIdProducts()) {
            ProductsDTO product;
            try {
                product = productsAPI.getProduct(codeProduct);
            } catch (Exception e) {
                throw new RuntimeException("Product not found with code: " + codeProduct);
            }

            shoppingCart.getIdProducts().add(codeProduct);
            totalCost += product.getPrice();
        }

        shoppingCart.setTotalCost(totalCost);
        return shopingCartRepository.save(shoppingCart);
    }


    @Override
    public ShopingCart editShopingCartAndAddProducts(ShopingCartDTO shopingCartDTO, Long idCart) {
        // Buscar el carrito de compras existente
        ShopingCart shopingCartEdited = this.findShopingCart(idCart);

        // Obtener los productos existentes en el carrito
        List<Long> existingProducts = shopingCartEdited.getIdProducts();
        if (existingProducts == null) {
            existingProducts = new ArrayList<>();
        }

        // Combinar los productos existentes con los nuevos productos
        List<Long> newProducts = shopingCartDTO.getIdProducts();
        existingProducts.addAll(newProducts);

        // Actualizar la lista de productos en el carrito
        shopingCartEdited.setIdProducts(existingProducts);

        // Calcular el costo total actualizado
        float totalCost = shopingCartEdited.getTotalCost();
        for (Long productId : newProducts) {
            ProductsDTO product = productsAPI.getProduct(productId); // Obtener el producto del servicio de productos
            totalCost += product.getPrice();
        }

        // Actualizar el costo total en el carrito
        shopingCartEdited.setTotalCost(totalCost);

        // Guardar y devolver el carrito de compras actualizado
        return shopingCartRepository.save(shopingCartEdited);
    }


    public ShopingCart fallbackGetShopingCart(Throwable throwable){
        return new ShopingCart(9999L,99999L,null);
   }



    @Override
    public void deleteShopingCart(Long id) {
        ShopingCart shopingCartDeleted = this.findShopingCart(id);
        this.shopingCartRepository.delete(shopingCartDeleted);
    }
}
