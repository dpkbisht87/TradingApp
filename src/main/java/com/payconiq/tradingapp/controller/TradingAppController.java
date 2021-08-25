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
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;


@RestController
@RequestMapping("/api")
@Api(value = "Trading-application")
public class TradingAppController {
    @Autowired
    private StockService stockService;

//    @RequestMapping("/")
//    public String getHelloWorld(){
//        System.out.println(java.time.Clock.systemUTC().instant());
//        return "Welcome to Trading App";
//    }
    
    @GetMapping("/stocks")
    public Response getStocks(){
        List<StockQueryDto> stockCreateDtos = stockService.getAllStocks();
        if(!stockCreateDtos.isEmpty()) {
            return Response.ok().setPayload(stockCreateDtos);
        }
        return Response.notFound()
                       .setErrors("No Stocks available.");
    }
    
    @PostMapping("/stocks")
    public Response createStock(@Valid  @RequestBody CreateStockRequest createStockRequest){
        StockQueryDto stockQueryDto = createNewStock(createStockRequest);
        if(stockQueryDto == null){
            return Response.notFound().setErrors("Stock " + createStockRequest.getName() + "already exists");
        } else {
            return Response.ok().setPayload(stockQueryDto);
        }
    }
    
    private StockQueryDto createNewStock(CreateStockRequest createStockRequest) {
        StockCreateDto stockCreateDto = new StockCreateDto()
                                                .setName(createStockRequest.getName())
                                                .setCurrentValue(createStockRequest.getPrice());
        return stockService.createStock(stockCreateDto);
    }
    
    @GetMapping("/stocks/{id}")
    public Response getStock(@PathVariable("id")  @Digits(integer=5, fraction=0) @DecimalMin(value = "0", inclusive = false) long id){
        return Response
                .ok()
                .setPayload(stockService.getStockById(id));
    }
    
    @PutMapping("/stocks/{id}")
    public Response updatePrice(@PathVariable("id")  @Digits(integer=5, fraction=0) @DecimalMin(value = "0", inclusive = false)  long id, @RequestBody @Valid UpdateStockRequest updateStockRequest){
        StockUpdateDto stockUpdateDto = new StockUpdateDto()
                                                .setCurrentValue(updateStockRequest.getPrice())
                                                .setLastUpdate(new Date());
        if(stockUpdateDto == null){
            return Response.notFound().setErrors("Stock with id " + id + "does not exist");
        } else {
            return Response
                    .ok()
                    .setPayload(stockService.updatePrice(id, stockUpdateDto));
        }
    }

    @DeleteMapping("/stocks/{id}")
    public Response deleteStockById(@PathVariable("id")  @Digits(integer=5, fraction=1) @DecimalMin(value = "0.0", inclusive = false)  long id){
        StockDeleteDto stockDeleteDto = new StockDeleteDto()
                                                .setId(id);
        if(stockDeleteDto == null){
            return Response.notFound().setErrors("Stock with id " + id + "does not exist");
        } else {
            return Response
                    .ok()
                    .setPayload(stockService.delete(stockDeleteDto));
        }
    }
}
