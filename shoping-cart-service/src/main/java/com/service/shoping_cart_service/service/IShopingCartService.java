package com.service.shoping_cart_service.service;

import com.service.shoping_cart_service.dto.ShopingCartDTO;
import com.service.shoping_cart_service.model.ShopingCart;

import java.util.List;

public interface IShopingCartService {

    public List<ShopingCart> getShopingsCart();

    public ShopingCart findShopingCart(Long id);

    public ShopingCart saveShoppingCartWithProducts(ShopingCartDTO shoppingCartRequest);

    public ShopingCart editShopingCartAndAddProducts(ShopingCartDTO shopingCartDTO, Long idCart);

    public void deleteShopingCart(Long id);
}
