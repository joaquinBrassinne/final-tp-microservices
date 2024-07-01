package com.service.sale_service.controller;

import com.service.sale_service.dto.SaleDTO;
import com.service.sale_service.model.Sale;
import com.service.sale_service.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    ISaleService iSaleService;

    @PostMapping("/save")
    public Sale saveSave(@RequestBody SaleDTO saleDTO){
        return this.iSaleService.saveSale(saleDTO);
    }

    @GetMapping("/{idCart}")
    public Sale findSale(@PathVariable Long idCart){
        return this.iSaleService.findSale(idCart);
    }
}
