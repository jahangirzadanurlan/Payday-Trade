package com.example.paydaytrade.controller;

import com.example.paydaytrade.model.dto.request.AuthenticationRequest;
import com.example.paydaytrade.model.dto.request.RegistrationRequest;
import com.example.paydaytrade.model.dto.response.AuthenticationResponse;
import com.example.paydaytrade.service.impl.security.impl.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AuthControllerTest {
    @Mock
    private AuthService authService;

    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authController = new AuthController(authService);
    }

    @Test
    void testRefreshToken() {
        String token = "your-token-here";
        AuthenticationResponse response = AuthenticationResponse.builder()
                .accessToken("new-access-token")
                .refreshToken("new-refresh-token")
                .build();

        when(authService.refreshToken(token)).thenReturn(response);

        AuthenticationResponse result = authController.refreshToken(token);

        assertEquals(response, result);
    }

    @Test
    void testRegistration() {
        RegistrationRequest request = new RegistrationRequest("username", "email","password");
        AuthenticationResponse response = AuthenticationResponse.builder()
                .accessToken("new-access-token")
                .refreshToken("new-refresh-token")
                .build();

        when(authService.registration(request)).thenReturn(response);

        AuthenticationResponse result = authController.registration(request);

        assertEquals(response, result);
    }

    @Test
    void testAuthentication() {
        AuthenticationRequest request = new AuthenticationRequest("username", "password");
        AuthenticationResponse response = AuthenticationResponse.builder()
                .accessToken("new-access-token")
                .refreshToken("new-refresh-token")
                .build();

        when(authService.authentication(request)).thenReturn(response);

        AuthenticationResponse result = authController.authentication(request);

        assertEquals(response, result);
    }

    @Test
    void testConfirmation() {
        UUID uuid = UUID.randomUUID();
        ResponseEntity<String> responseEntity = ResponseEntity.ok("Account confirmed");

        when(authService.confirmAccount(uuid)).thenReturn(responseEntity);

        ResponseEntity<String> result = authController.confirmation(uuid);

        assertEquals(responseEntity, result);
    }
}