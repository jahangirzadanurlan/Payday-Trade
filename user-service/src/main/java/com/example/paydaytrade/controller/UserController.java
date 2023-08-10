package com.example.paydaytrade.controller;

import com.example.paydaytrade.model.dto.request.BuySellRequestDto;
import com.example.paydaytrade.model.dto.request.DepositRequestDto;
import com.example.paydaytrade.model.dto.response.ResponseDto;
import com.example.paydaytrade.service.IStockService;
import com.example.paydaytrade.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final IStockService stockService;
    private final IUserService userService;


    @PostMapping("/buy-stock")
    public ResponseEntity<ResponseDto> buyStock(@RequestBody BuySellRequestDto buySellRequestDto){
        return ResponseEntity.ok().body(stockService.buyStock(buySellRequestDto));
    }

    @PostMapping("/sell-stock")
    public ResponseEntity<ResponseDto> sellStock(@RequestBody BuySellRequestDto buySellRequestDto){
        return ResponseEntity.ok().body(stockService.sellStock(buySellRequestDto));
    }

    @PutMapping("/deposit")
    public ResponseEntity<ResponseDto> depositMoney(@RequestBody DepositRequestDto depositRequestDto){
        return userService.depositMoney(depositRequestDto);
    }


}

