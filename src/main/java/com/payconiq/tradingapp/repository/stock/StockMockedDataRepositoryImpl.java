package com.payconiq.tradingapp.repository.stock;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.payconiq.tradingapp.model.stock.Stock;
import org.springframework.stereotype.Component;

@Component
public class StockMockedDataRepositoryImpl implements  StockMockedDataRepository {
    
    private static List<Stock> stocks;
    
    private static StockMockedDataRepositoryImpl instance = null;
    public static StockMockedDataRepositoryImpl getInstance(){
        if(instance == null){
            instance = new StockMockedDataRepositoryImpl();
        }
        return instance;
    }
    
    public StockMockedDataRepositoryImpl() {
        stocks = new ArrayList<Stock>();
        Stock s1 = new Stock();
        s1.setId(1);
        s1.setName("A");
        s1.setPrice(new BigDecimal("1000"));
        stocks.add(s1);
    
        Stock s2 = new Stock();
        s2.setId(2);
        s2.setName("B");
        s2.setPrice(new BigDecimal("2000"));
        stocks.add(s2);
    
        Stock s3 = new Stock();
        s3.setId(3);
        s3.setName("C");
        s3.setPrice(new BigDecimal("3000"));
        stocks.add(s3);
    
        Stock s4 = new Stock();
        s4.setId(4);
        s4.setName("D");
        s4.setPrice(new BigDecimal("4000"));
        stocks.add(s4);
    }
    
    public List<Stock> getAllStocks(){
        return stocks;
    }
    
    public Stock save(Stock stock){
        stocks.add(stock);
        return stock;
    }
    
    public Stock update(Stock stock){
        int stockIndex = -1;
        for(Stock currStock: stocks) {
            if(currStock.getId().equals(stock.getId())) {
                stockIndex = stocks.indexOf(currStock);
                break;
            }
        }
        if(stockIndex > -1) {
            stocks.set(stockIndex, stock);
            return stock;
        }
        return null;
    }
    public Stock findById(int id){
        for(Stock stock: stocks) {
            if(stock.getId() == id) {
                return stock;
            }
        }
        return null;
    }
    
    public Stock delete(int id) {
        int stockIndex = -1;
        for(Stock stock: stocks) {
            if(stock.getId() == id) {
                stockIndex = stocks.indexOf(stock);
                break;
            }
        }
        Stock deletedstock = null;
        if(stockIndex > -1) {
            deletedstock = stocks.remove(stockIndex);
        }
        return deletedstock;
    }
}
