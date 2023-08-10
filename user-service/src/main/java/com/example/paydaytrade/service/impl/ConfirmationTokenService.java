package com.example.paydaytrade.service.impl;

import com.example.paydaytrade.exception.ApplicationException;
import com.example.paydaytrade.model.dto.response.ResponseDto;
import com.example.paydaytrade.model.entity.ConfirmationToken;
import com.example.paydaytrade.model.enums.Exceptions;
import com.example.paydaytrade.repository.ConfirmationTokenRepository;
import com.example.paydaytrade.service.IConfirmationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfirmationTokenService implements IConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Override
    public ResponseDto save(ConfirmationToken confirmationToken) {
        ConfirmationToken save = confirmationTokenRepository.save(confirmationToken);
        if (save != null){
            return new ResponseDto("Save is successfull");
        } else {
            throw new ApplicationException(Exceptions.TOKEN_IS_INVALID_EXCEPTION);
        }
    }

    @Override
    public ConfirmationToken getTokenByUUID(String uuid) {
        return confirmationTokenRepository.findConfirmationTokenByToken(uuid)
                .orElseThrow(() -> new ApplicationException(Exceptions.TOKEN_NOT_FOUND_EXCEPTION));
    }
}
