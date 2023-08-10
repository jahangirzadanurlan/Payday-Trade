package com.example.paydaytrade.service;

import com.example.paydaytrade.model.dto.request.SellRequestDto;
import com.example.paydaytrade.model.entity.Stock;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "stock-service",url = "http://localhost:8080")
public interface StockServiceClient {

    @GetMapping("/stock")
    Stock findStockBySymbol(@RequestParam String symbol);

    @PostMapping("/stock/sell")
    int sellStock(@RequestBody SellRequestDto sellRequestDTO);

}
