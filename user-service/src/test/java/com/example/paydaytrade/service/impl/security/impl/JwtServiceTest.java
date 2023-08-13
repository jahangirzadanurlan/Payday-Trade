package com.example.paydaytrade.service.impl.security.impl;

import io.jsonwebtoken.Claims;

import java.util.Date;
import java.util.Map;

import java.util.function.Function;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {JwtService.class})
@ExtendWith(SpringExtension.class)
class JwtServiceTest {
    @Autowired
    private JwtService jwtService;

    @Test
    @Disabled("TODO: Complete this test")
    void testExtractAllClaims() {
        String token = "";

        // Act
        Claims actualExtractAllClaimsResult = this.jwtService.extractAllClaims(token);

        // Assert
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testExtractClaim() {
        // Arrange
        // TODO: Populate arranged inputs
        String token = "";
        Function<Claims, Object> claimsResolver = null;

        // Act
        Object actualExtractClaimResult = this.jwtService.extractClaim(token, claimsResolver);

        // Assert
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testExtractUsername() {
        String token = "";

        // Act
        String actualExtractUsernameResult = this.jwtService.extractUsername(token);

        // Assert
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testExtractExpiration() {
        // Arrange
        String token = "";

        // Act
        Date actualExtractExpirationResult = this.jwtService.extractExpiration(token);

        // Assert
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testGenerateToken() {
        // Arrange
        Map<String, Object> extractClaims = null;
        UserDetails userDetails = null;
        long accessTokenExpiration = 0L;

        // Act
        String actualGenerateTokenResult = this.jwtService.generateToken(extractClaims, userDetails,
                accessTokenExpiration);

        // Assert
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testGenerateToken2() {
        // Arrange
        UserDetails userDetails = null;

        // Act
        String actualGenerateTokenResult = this.jwtService.generateToken(userDetails);

        // Assert
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testGenerateRefreshToken() {

        // Arrange
        // TODO: Populate arranged inputs
        Map<String, Object> extractClaims = null;
        UserDetails userDetails = null;
        long refreshTokenExpiration = 0L;

        // Act
        String actualGenerateRefreshTokenResult = this.jwtService.generateRefreshToken(extractClaims, userDetails,
                refreshTokenExpiration);

        // Assert
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testGenerateRefreshToken2() {
        // Arrange
        UserDetails userDetails = null;

        // Act
        String actualGenerateRefreshTokenResult = this.jwtService.generateRefreshToken(userDetails);

        // Assert
    }

    /**
     * Method under test: {@link JwtService#isTokenExpired(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testIsTokenExpired() {
        // Arrange
        String token = "";

        // Act
        boolean actualIsTokenExpiredResult = this.jwtService.isTokenExpired(token);

        // Assert
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testIsTokenValid() {
        // Arrange
        String token = "";
        UserDetails userDetails = null;

        // Act
        boolean actualIsTokenValidResult = this.jwtService.isTokenValid(token, userDetails);

        // Assert
    }
}

