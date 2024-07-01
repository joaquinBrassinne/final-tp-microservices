package com.service.sale_service.service;

import com.service.sale_service.dto.SaleDTO;
import com.service.sale_service.model.Sale;

import java.util.List;

public interface ISaleService {

    public Sale saveSale(SaleDTO saleDTO);
    public List<Sale> getSale();
    public Sale findSale(Long idSale);
    public void deleteSale(Long idSale);
}
