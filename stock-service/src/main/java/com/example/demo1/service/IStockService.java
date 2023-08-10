package com.example.demo1.service;

import com.example.demo1.model.dto.request.SellRequestDto;
import com.example.demo1.model.dto.response.StockResponseDto;
import com.example.demo1.model.entity.Stock;

import java.util.List;

public interface IStockService {
    StockResponseDto findStockByName(String name);
    List<Stock> findAllStocks();
    Stock findStockBySymbol(String symbol);
    int sellStock(SellRequestDto sellRequestDto);

}
