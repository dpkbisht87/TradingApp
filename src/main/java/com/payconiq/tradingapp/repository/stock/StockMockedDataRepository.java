package com.payconiq.tradingapp.repository.stock;

import com.payconiq.tradingapp.model.stock.Stock;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockMockedDataRepository {
    Stock save(Stock stock);
    
    Stock update(Stock stock);
    
    Stock findById(Long id);
    
    Stock findByName(String name);
    
    List<Stock> getAllStocks();
    
    Stock delete(Long id);
}
