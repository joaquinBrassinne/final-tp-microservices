package com.service.shoping_cart_service.repository;

import com.service.shoping_cart_service.model.ShopingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IShopingCartRepository extends JpaRepository<ShopingCart, Long> {
}
