package com.example.paydaytrade.service;

import com.example.paydaytrade.model.dto.response.ResponseDto;
import com.example.paydaytrade.model.entity.User;

public interface IWalletService {
    ResponseDto increaseBalance(int price,User user);
    ResponseDto decreaseBalance(int price,User user);

    boolean checkBalance(int offerPrice, User user);

}

