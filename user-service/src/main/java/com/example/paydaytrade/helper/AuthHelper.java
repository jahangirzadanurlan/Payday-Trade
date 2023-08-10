package com.example.paydaytrade.helper;

import com.example.paydaytrade.model.entity.Token;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthHelper {
    public Token buildToken(Token token,String jwt){
        return Token.builder()
                .id(token != null ? token.getId() : null)
                .token(jwt)
                .expired(false)
                .revoked(false)
                .createdAt(LocalDateTime.now())
                .build();
    }

}
