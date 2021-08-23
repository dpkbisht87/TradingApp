package com.payconiq.tradingapp.dto.mapper;

import com.payconiq.tradingapp.dto.model.StockQueryDto;
import com.payconiq.tradingapp.model.stock.Stock;
import org.springframework.stereotype.Component;

@Component
public class StockMapper {
    public static StockQueryDto toStockDto(Stock stock){
        return new StockQueryDto()
                       .setId(stock.getId())
                       .setName(stock.getName())
                       .setCurrentValue(stock.getPrice())
                       .setLastUpdate(stock.getLastUpdate());
    }
}
