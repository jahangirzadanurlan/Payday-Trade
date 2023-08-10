package com.example.demo1.service.Impl;

import com.example.demo1.exception.ApplicationException;
import com.example.demo1.model.dto.request.SellRequestDto;
import com.example.demo1.model.dto.response.StockResponseDto;
import com.example.demo1.model.entity.Stock;
import com.example.demo1.model.enums.Exceptions;
import com.example.demo1.repository.StockRepository;
import com.example.demo1.service.IStockService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockService implements IStockService {
    private final StockRepository stockRepository;
    private final ModelMapper modelMapper;

    @Override
    public StockResponseDto findStockByName(String name) {
        StockResponseDto stock = modelMapper.map(stockRepository.findStockByName(name), StockResponseDto.class);
        if (stock != null){
            return stock;
        }else {
            throw new ApplicationException(Exceptions.STOCK_NOT_FOUND_EXCEPTION);
        }
    }

    @Override
    public List<Stock> findAllStocks() {
        List<Stock> stockList = stockRepository.findAll();
        if (!stockList.isEmpty()){
            return stockList;
        }else {
            throw new ApplicationException(Exceptions.STOCK_LIST_IS_EMPTY_EXCEPTION);
        }
    }

    @Override
    public Stock findStockBySymbol(String symbol) {
        Optional<Stock> stock = stockRepository.findStockBySymbol(symbol);
        return stock.orElseThrow(() -> new ApplicationException(Exceptions.STOCK_NOT_FOUND_EXCEPTION));
    }

    @Override
    public int sellStock(SellRequestDto sellRequestDto) {
        Optional<Stock> stock = stockRepository.findStockBySymbol(sellRequestDto.getSymbol());
        int price = stock.orElseThrow(() -> new ApplicationException(Exceptions.STOCK_NOT_FOUND_EXCEPTION)).getPrice();

        if (price >= sellRequestDto.getOfferPrice()){
            return price;
        }else {
            return 0;
        }
    }

}
