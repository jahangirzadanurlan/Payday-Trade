package com.example.demo1.controller;

import com.example.demo1.model.dto.request.SellRequestDto;
import com.example.demo1.model.entity.Stock;
import com.example.demo1.service.IStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StockController {
    private final IStockService stockService;

    @GetMapping("/stocks")
    public List<Stock> showStockData() {
        return stockService.findAllStocks();
    }

    @GetMapping("/stock")
    public Stock stockBySymbol(@RequestParam String symbol) {
        return stockService.findStockBySymbol(symbol);
    }

    @PostMapping("/stock/sell")
    public int stockById(@RequestBody SellRequestDto sellRequestDto) {
        return stockService.sellStock(sellRequestDto);
    }
}


