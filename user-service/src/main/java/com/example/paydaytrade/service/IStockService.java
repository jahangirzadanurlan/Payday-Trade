package com.example.paydaytrade.service;

import com.example.paydaytrade.model.dto.request.BuySellRequestDto;
import com.example.paydaytrade.model.dto.response.ResponseDto;

public interface IStockService {
    ResponseDto buyStock( BuySellRequestDto buySellRequestDto);
    ResponseDto sellStock(BuySellRequestDto buySellRequestDto);
}
