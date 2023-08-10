package com.example.paydaytrade.service;

import com.example.paydaytrade.model.dto.request.DepositRequestDto;
import com.example.paydaytrade.model.dto.response.ResponseDto;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    ResponseEntity<ResponseDto> depositMoney(DepositRequestDto depositRequestDto);
}
