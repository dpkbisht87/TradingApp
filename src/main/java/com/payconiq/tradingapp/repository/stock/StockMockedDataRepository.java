package com.payconiq.tradingapp.repository.stock;

import com.payconiq.tradingapp.model.stock.Stock;
import java.util.List;

public interface StockMockedDataRepository {
    Stock save(Stock stock);
    Stock update(Stock stock);
    Stock findById(int id);
    List<Stock> getAllStocks();
    Stock delete(int id);
}
