package com.example.paydaytrade.service;

import com.example.paydaytrade.model.dto.response.ResponseDto;
import com.example.paydaytrade.model.entity.ConfirmationToken;

public interface IConfirmationTokenService {
    ResponseDto save(ConfirmationToken confirmationToken);
    ConfirmationToken getTokenByUUID(String uuid);
}
