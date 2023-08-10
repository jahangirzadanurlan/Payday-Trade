package com.example.paydaytrade.service.impl;

import com.example.paydaytrade.model.dto.response.ResponseDto;
import com.example.paydaytrade.model.entity.User;
import com.example.paydaytrade.repository.UserRepository;
import com.example.paydaytrade.service.IWalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletService implements IWalletService{
    private final UserRepository userRepository;


    @Override
    public ResponseDto increaseBalance(int price,User user) {
        int wallet = user.getWallet();
        user.setWallet(wallet + price);

        userRepository.save(user);
        return new ResponseDto(price + " dollar loaded into the wallet");
    }

    @Override
    public ResponseDto decreaseBalance(int price,User user) {
        int wallet = user.getWallet();
        user.setWallet(wallet - price);

        userRepository.save(user);
        return new ResponseDto(price + " was deducted from the wallet");
    }

    @Override
    public boolean checkBalance(int offerPrice,User user) {
        return user.getWallet() >= offerPrice;
    }
}
