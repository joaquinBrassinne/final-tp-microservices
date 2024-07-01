package com.service.sale_service.service;

import com.service.sale_service.dto.SaleDTO;
import com.service.sale_service.dto.ShopingCartDTO;
import com.service.sale_service.model.Sale;
import com.service.sale_service.repository.ISaleRepository;
import com.service.sale_service.repository.IShopingCartAPI;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService implements ISaleService{

    @Autowired
    private ISaleRepository saleRepository;

    @Autowired
    IShopingCartAPI shopingCartAPI;

    @Override
    @CircuitBreaker(name = "shoping-cart", fallbackMethod = "fallBackFindShopingCart")
    @Retry(name = "shoping-cart")
    public Sale saveSale(SaleDTO saleDTO) {
        Sale saleSave = new Sale();

        ShopingCartDTO shopingCart;
        try {
            shopingCart = this.shopingCartAPI.findShopingCart(saleDTO.getIdCart());
        }catch (Exception e){
            throw new RuntimeException("We dont found the Cart with that id");
        }

        saleSave.setIdSale(saleDTO.getIdSale());
        saleSave.setIdCart(saleDTO.getIdCart());
        saleSave.setTotalCost(shopingCart.getTotalCost());
        saleSave.setCreationDate(saleDTO.getCreationDate());

        return this.saleRepository.save(saleSave);
    }

    public Sale fallBackFindShopingCart(Throwable throwable){
        return new Sale(9999L,9999L,null,999999);
    }

    @Override
    public List<Sale> getSale() {
        return this.saleRepository.findAll();
    }

    @Override
    public Sale findSale(Long idSale) {
        return saleRepository.findById(idSale)
                .orElseThrow(()-> new RuntimeException("Sale with that id doesnt exist"));
    }

    @Override
    public void deleteSale(Long idSale) {
        Sale saleDeleted = this.findSale(idSale);
        this.saleRepository.delete(saleDeleted);
    }
}
