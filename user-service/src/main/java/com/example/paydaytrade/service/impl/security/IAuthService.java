package com.example.paydaytrade.service.impl.security;

import com.example.paydaytrade.model.dto.request.AuthenticationRequest;
import com.example.paydaytrade.model.dto.request.RegistrationRequest;
import com.example.paydaytrade.model.dto.response.AuthenticationResponse;

public interface IAuthService {
    AuthenticationResponse registration(RegistrationRequest request);
    AuthenticationResponse authentication(AuthenticationRequest request);
    AuthenticationResponse refreshToken(String authHeader);

}
