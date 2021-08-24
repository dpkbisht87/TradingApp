package com.payconiq.tradingapp.service;

import com.payconiq.tradingapp.dto.model.StockCreateDto;
import com.payconiq.tradingapp.dto.mapper.StockMapper;
import com.payconiq.tradingapp.dto.model.StockDeleteDto;
import com.payconiq.tradingapp.dto.model.StockQueryDto;
import com.payconiq.tradingapp.dto.model.StockUpdateDto;
import com.payconiq.tradingapp.exception.EntityType;
import com.payconiq.tradingapp.exception.ExceptionType;
import com.payconiq.tradingapp.exception.StockException;
import com.payconiq.tradingapp.model.stock.Stock;
import com.payconiq.tradingapp.repository.stock.StockMockedDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.payconiq.tradingapp.exception.EntityType.STOCK;
import static com.payconiq.tradingapp.exception.ExceptionType.DUPLICATE_ENTITY;
import static com.payconiq.tradingapp.exception.ExceptionType.ENTITY_NOT_FOUND;
import static com.payconiq.tradingapp.exception.ExceptionType.UPDATE_FAILED_EXCEPTION;

@Component
public class StockServiceImpl implements StockService {
    
    @Autowired
    private StockMockedDataRepository stockMockedDataRepository;
    
    @Override
    public List<StockQueryDto> getAllStocks() {
        List<Stock> stocks = stockMockedDataRepository.getAllStocks();
        List<StockQueryDto> stocksDto = new ArrayList<>();
        if (stocks.size() != 0) {
            for (Stock stock : stocks) {
                stocksDto.add(StockMapper.toStockDto(stock));
            }
            return stocksDto;
        }
        throw exception(STOCK, ENTITY_NOT_FOUND, "");
    }
    
    @Override
    public StockQueryDto getStockById(int id) {
        Stock stock = stockMockedDataRepository.findById(id);
        if (stock != null) {
            return StockMapper.toStockDto(stock);
        }
        throw exception(STOCK, ENTITY_NOT_FOUND, String.valueOf(id));
    }
    
    @Override
    public StockQueryDto createStock(StockCreateDto stockCreateDto) {
        System.out.println("Create Stock");
        Stock stock = stockMockedDataRepository.findById(stockCreateDto.getId());
        if (stock == null) {
            stock = new Stock();
            stock.setId(stockCreateDto.getId());
            stock.setName(stockCreateDto.getName());
            stock.setPrice(stockCreateDto.getCurrentValue());
            stock.setLastUpdate(new Date());
            return StockMapper.toStockDto(stockMockedDataRepository.save(stock));
        }
        throw exception(STOCK, DUPLICATE_ENTITY, String.valueOf(stockCreateDto.getId()));
    }
    
    @Override
    public StockQueryDto updatePrice(int id, StockUpdateDto stockUpdateDto) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Stock stock = stockMockedDataRepository.findById(id);
        if (stock != null) {
            stock.setPrice(stockUpdateDto.getCurrentValue());
            stock.setLastUpdate(stockUpdateDto.getLastUpdate());
            return StockMapper.toStockDto(stockMockedDataRepository.update(stock));
        }
        throw exception(STOCK, ENTITY_NOT_FOUND, String.valueOf(id));
    }
    
    @Override
    public StockQueryDto delete(StockDeleteDto stockDeleteDto) {
        Stock stock = stockMockedDataRepository.findById(stockDeleteDto.getId());
        if(stock != null){
            return StockMapper.toStockDto(stockMockedDataRepository.delete(stockDeleteDto.getId()));
        }
        throw exception(STOCK, ENTITY_NOT_FOUND, String.valueOf(stockDeleteDto.getId()));
    }
    
    /**
     * Returns a new RuntimeException
     *
     * @param entityType
     * @param exceptionType
     * @param args
     * @return
     */
    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return StockException.throwException(entityType, exceptionType, args);
    }
    
    /**
     * Returns a new RuntimeException
     *
     * @param entityType
     * @param exceptionType
     * @param args
     * @return
     */
    private RuntimeException exceptionWithId(EntityType entityType, ExceptionType exceptionType, String id, String... args) {
        return StockException.throwExceptionWithId(entityType, exceptionType, id, args);
    }
}