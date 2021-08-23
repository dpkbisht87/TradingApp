package com.payconiq.tradingapp.controller;

import java.util.Date;
import java.util.List;

import com.payconiq.tradingapp.controller.request.CreateStockRequest;
import com.payconiq.tradingapp.controller.request.DeleteStockRequest;
import com.payconiq.tradingapp.controller.request.UpdateStockRequest;
import com.payconiq.tradingapp.dto.model.StockCreateDto;
import com.payconiq.tradingapp.dto.model.StockDeleteDto;
import com.payconiq.tradingapp.dto.model.StockQueryDto;
import com.payconiq.tradingapp.dto.model.StockUpdateDto;
import com.payconiq.tradingapp.dto.response.Response;
import com.payconiq.tradingapp.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class TradingAppController {
    @Autowired
    private StockService stockService;

    @RequestMapping("/")
    public String getHelloWorld(){
        System.out.println(java.time.Clock.systemUTC().instant());
        return "Welcome to Trading App";
    }
    
    @GetMapping("/api/stocks")
    public Response getStocks(){
        List<StockQueryDto> stockCreateDtos = stockService.getAllStocks();
        if(!stockCreateDtos.isEmpty()) {
            return Response.ok().setPayload(stockCreateDtos);
        }
        return Response.notFound()
                       .setErrors("No Stocks available.");
    }
    
    @PostMapping("/api/stocks")
    public Response createStock(@RequestBody @Valid CreateStockRequest createStockRequest){
        StockQueryDto stockQueryDto = createNewStock(createStockRequest);
        if(stockQueryDto == null){
            return Response.notFound().setErrors("Stock with id " + createStockRequest.getId() + "already exists");
        } else {
            return Response.ok().setPayload(stockQueryDto);
        }
    }
    
    private StockQueryDto createNewStock(CreateStockRequest createStockRequest) {
        StockCreateDto stockCreateDto = new StockCreateDto()
                                    .setId(createStockRequest.getId())
                                    .setName(createStockRequest.getName())
                                    .setCurrentValue(createStockRequest.getPrice());
        return stockService.createStock(stockCreateDto);
    }
    
    @GetMapping("/api/stocks/{id}")
    public Response getStock(@PathVariable int id){
        return Response
                .ok()
                .setPayload(stockService.getStockById(id));
    }
    
    @PutMapping("/api/stocks/{id}")
    public StockQueryDto updatePrice(@PathVariable String id, @RequestBody @Valid UpdateStockRequest updateStockRequest){
        StockUpdateDto stockUpdateDto = new StockUpdateDto()
                                                .setCurrentValue(updateStockRequest.getPrice())
                                                .setLastUpdate(new Date());
        return stockService.updatePrice(Integer.parseInt(id), stockUpdateDto);
    }

    @DeleteMapping("/api/stocks/{id}")
    public StockQueryDto deleteStockById(@PathVariable String id){
        StockDeleteDto stockDeleteDto = new StockDeleteDto()
                                                .setId(Integer.parseInt(id));
        return stockService.delete(stockDeleteDto);
    }
                          
}
