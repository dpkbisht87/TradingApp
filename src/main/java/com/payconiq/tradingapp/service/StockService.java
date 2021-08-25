package com.payconiq.tradingapp.service;

import com.payconiq.tradingapp.dto.model.StockCreateDto;
import com.payconiq.tradingapp.dto.model.StockDeleteDto;
import com.payconiq.tradingapp.dto.model.StockQueryDto;
import com.payconiq.tradingapp.dto.model.StockUpdateDto;

import javax.persistence.LockModeType;
import java.util.List   ;

public interface StockService {
    
    // Get all Stocks
    List<StockQueryDto> getAllStocks();
    
    // Get Stock by Id
    StockQueryDto getStockById(long id);
    
    // Create a new Stock
    StockQueryDto createStock(StockCreateDto stockCreateDto);
    
    // Update the price of existing Stock
    StockQueryDto updatePrice(long id, StockUpdateDto stockUpdateDto);

    // Delete the Stock by Id
    StockQueryDto delete(StockDeleteDto stockDeleteDto);
}
