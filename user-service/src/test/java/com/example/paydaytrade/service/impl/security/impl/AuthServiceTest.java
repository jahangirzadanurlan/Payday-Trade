package com.example.paydaytrade.service.impl.security.impl;

import com.example.paydaytrade.model.dto.request.AuthenticationRequest;
import com.example.paydaytrade.model.dto.request.RegistrationRequest;
import com.example.paydaytrade.model.dto.response.AuthenticationResponse;
import com.example.paydaytrade.model.entity.ConfirmationToken;
import com.example.paydaytrade.model.entity.Role;
import com.example.paydaytrade.model.entity.Token;
import com.example.paydaytrade.model.entity.User;
import com.example.paydaytrade.model.enums.RoleType;
import com.example.paydaytrade.repository.RoleRepository;
import com.example.paydaytrade.repository.TokenRepository;
import com.example.paydaytrade.repository.UserRepository;
import com.example.paydaytrade.security.SecurityHelper;
import com.example.paydaytrade.service.IConfirmationTokenService;
import com.example.paydaytrade.service.impl.MailSenderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private SecurityHelper securityHelper;

    @Mock
    private TokenRepository tokenRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private MailSenderService mailSenderService;

    @Mock
    private IConfirmationTokenService confirmationTokenService;

    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authService = new AuthService(
                userRepository,
                authenticationManager,
                roleRepository,
                jwtService,
                securityHelper,
                tokenRepository,
                passwordEncoder,
                mailSenderService,
                confirmationTokenService
        );
    }

    @Test
    void testConfirmAccount() {
        UUID uuid = UUID.randomUUID();
        ConfirmationToken token = ConfirmationToken.builder()
                .token(uuid.toString())
                .user(new User())
                .build();
        when(confirmationTokenService.getTokenByUUID(uuid.toString())).thenReturn(token);

        ResponseEntity<String> result = authService.confirmAccount(uuid);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("email@example.com Confirmation is successfully", result.getBody());
    }

    @Test
    void testRegistration() {
        RegistrationRequest registrationRequest = new RegistrationRequest("username", "password", "email@example.com");
        Role role = Role.builder()
                .name(RoleType.USER)
                .build();
        User user = User.builder()
                .username(registrationRequest.getUsername())
                .email(registrationRequest.getEmail())
                .role(role)
                .build();

        when(roleRepository.findRoleByName(RoleType.USER)).thenReturn(Optional.of(role));
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(passwordEncoder.encode(registrationRequest.getPassword())).thenReturn("encodedPassword");
        when(jwtService.generateToken(any(User.class))).thenReturn("accessToken");
        when(jwtService.generateRefreshToken(any(User.class))).thenReturn("refreshToken");

        AuthenticationResponse authenticationResponse = authService.registration(registrationRequest);

        assertEquals("A confirmation email was sent to email@example.com. Please verify your account by clicking on the link ", authenticationResponse.getMessage());
        assertEquals("accessToken", authenticationResponse.getAccessToken());
        assertEquals("refreshToken", authenticationResponse.getRefreshToken());
    }

    @Test
    void testAuthentication() {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("username", "password");
        User user = User.builder()
                .username(authenticationRequest.getUsername())
                .password(passwordEncoder.encode(authenticationRequest.getPassword()))
                .build();

        when(authenticationManager.authenticate(any())).thenReturn(null);
        when(userRepository.findUserByUsernameOrEmail(authenticationRequest.getUsername())).thenReturn(Optional.of(user));
        when(jwtService.generateToken(user)).thenReturn("accessToken");
        when(jwtService.generateRefreshToken(user)).thenReturn("refreshToken");
        when(tokenRepository.findTokenByUser(user)).thenReturn(Optional.of(new Token()));

        AuthenticationResponse authenticationResponse = authService.authentication(authenticationRequest);

        assertEquals("A confirmation email was sent to null. Please verify your account by clicking on the link ", authenticationResponse.getMessage());
        assertEquals("accessToken", authenticationResponse.getAccessToken());
        assertEquals("refreshToken", authenticationResponse.getRefreshToken());
    }

    @Test
    void testRefreshToken() {
        String authHeader = "Bearer your-jwt-token";
        User user = User.builder()
                .username("username")
                .build();
        Token token = Token.builder()
                .token("oldAccessToken")
                .user(user)
                .build();

        when(securityHelper.authHeaderIsValid(authHeader)).thenReturn(true);
        when(jwtService.extractUsername("your-jwt-token")).thenReturn("username");
        when(userRepository.findUserByUsernameOrEmail("username")).thenReturn(Optional.of(user));
        when(jwtService.isTokenValid("your-jwt-token", user)).thenReturn(true);
        when(jwtService.generateToken(user)).thenReturn("newAccessToken");
        when(jwtService.generateRefreshToken(user)).thenReturn("newRefreshToken");
        when(tokenRepository.findTokenByUser(user)).thenReturn(Optional.of(token));

        AuthenticationResponse authenticationResponse = authService.refreshToken(authHeader);

        assertEquals("newAccessToken", authenticationResponse.getAccessToken());
        assertEquals("newRefreshToken", authenticationResponse.getRefreshToken());
    }

}