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

    /**
     * Method under test: {@link JwtService#extractAllClaims(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testExtractAllClaims() {
        // TODO: Complete this test.
        //   Reason: R033 Missing Spring properties.
        //   Failed to create Spring context due to unresolvable @Value
        //   properties: java.lang.Long com.example.paydaytrade.service.impl.security.impl.JwtService.accessTokenExpiration
        //   Please check that at least one of the property files is provided
        //   and contains required variables:
        //   - application-test.properties (file missing)
        //   See https://diff.blue/R033 to resolve this issue.

        // Arrange
        // TODO: Populate arranged inputs
        String token = "";

        // Act
        Claims actualExtractAllClaimsResult = this.jwtService.extractAllClaims(token);

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link JwtService#extractClaim(String, Function)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testExtractClaim() {
        // TODO: Complete this test.
        //   Reason: R033 Missing Spring properties.
        //   Failed to create Spring context due to unresolvable @Value
        //   properties: java.lang.Long com.example.paydaytrade.service.impl.security.impl.JwtService.accessTokenExpiration
        //   Please check that at least one of the property files is provided
        //   and contains required variables:
        //   - application-test.properties (file missing)
        //   See https://diff.blue/R033 to resolve this issue.

        // Arrange
        // TODO: Populate arranged inputs
        String token = "";
        Function<Claims, Object> claimsResolver = null;

        // Act
        Object actualExtractClaimResult = this.jwtService.extractClaim(token, claimsResolver);

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link JwtService#extractUsername(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testExtractUsername() {
        // TODO: Complete this test.
        //   Reason: R033 Missing Spring properties.
        //   Failed to create Spring context due to unresolvable @Value
        //   properties: java.lang.Long com.example.paydaytrade.service.impl.security.impl.JwtService.accessTokenExpiration
        //   Please check that at least one of the property files is provided
        //   and contains required variables:
        //   - application-test.properties (file missing)
        //   See https://diff.blue/R033 to resolve this issue.

        // Arrange
        // TODO: Populate arranged inputs
        String token = "";

        // Act
        String actualExtractUsernameResult = this.jwtService.extractUsername(token);

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link JwtService#extractExpiration(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testExtractExpiration() {
        // TODO: Complete this test.
        //   Reason: R033 Missing Spring properties.
        //   Failed to create Spring context due to unresolvable @Value
        //   properties: java.lang.Long com.example.paydaytrade.service.impl.security.impl.JwtService.accessTokenExpiration
        //   Please check that at least one of the property files is provided
        //   and contains required variables:
        //   - application-test.properties (file missing)
        //   See https://diff.blue/R033 to resolve this issue.

        // Arrange
        // TODO: Populate arranged inputs
        String token = "";

        // Act
        Date actualExtractExpirationResult = this.jwtService.extractExpiration(token);

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link JwtService#generateToken(Map, UserDetails, long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGenerateToken() {
        // TODO: Complete this test.
        //   Reason: R033 Missing Spring properties.
        //   Failed to create Spring context due to unresolvable @Value
        //   properties: java.lang.Long com.example.paydaytrade.service.impl.security.impl.JwtService.accessTokenExpiration
        //   Please check that at least one of the property files is provided
        //   and contains required variables:
        //   - application-test.properties (file missing)
        //   See https://diff.blue/R033 to resolve this issue.

        // Arrange
        // TODO: Populate arranged inputs
        Map<String, Object> extractClaims = null;
        UserDetails userDetails = null;
        long accessTokenExpiration = 0L;

        // Act
        String actualGenerateTokenResult = this.jwtService.generateToken(extractClaims, userDetails,
                accessTokenExpiration);

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link JwtService#generateToken(UserDetails)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGenerateToken2() {
        // TODO: Complete this test.
        //   Reason: R033 Missing Spring properties.
        //   Failed to create Spring context due to unresolvable @Value
        //   properties: java.lang.Long com.example.paydaytrade.service.impl.security.impl.JwtService.accessTokenExpiration
        //   Please check that at least one of the property files is provided
        //   and contains required variables:
        //   - application-test.properties (file missing)
        //   See https://diff.blue/R033 to resolve this issue.

        // Arrange
        // TODO: Populate arranged inputs
        UserDetails userDetails = null;

        // Act
        String actualGenerateTokenResult = this.jwtService.generateToken(userDetails);

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link JwtService#generateRefreshToken(Map, UserDetails, long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGenerateRefreshToken() {
        // TODO: Complete this test.
        //   Reason: R033 Missing Spring properties.
        //   Failed to create Spring context due to unresolvable @Value
        //   properties: java.lang.Long com.example.paydaytrade.service.impl.security.impl.JwtService.accessTokenExpiration
        //   Please check that at least one of the property files is provided
        //   and contains required variables:
        //   - application-test.properties (file missing)
        //   See https://diff.blue/R033 to resolve this issue.

        // Arrange
        // TODO: Populate arranged inputs
        Map<String, Object> extractClaims = null;
        UserDetails userDetails = null;
        long refreshTokenExpiration = 0L;

        // Act
        String actualGenerateRefreshTokenResult = this.jwtService.generateRefreshToken(extractClaims, userDetails,
                refreshTokenExpiration);

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link JwtService#generateRefreshToken(UserDetails)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGenerateRefreshToken2() {
        // TODO: Complete this test.
        //   Reason: R033 Missing Spring properties.
        //   Failed to create Spring context due to unresolvable @Value
        //   properties: java.lang.Long com.example.paydaytrade.service.impl.security.impl.JwtService.accessTokenExpiration
        //   Please check that at least one of the property files is provided
        //   and contains required variables:
        //   - application-test.properties (file missing)
        //   See https://diff.blue/R033 to resolve this issue.

        // Arrange
        // TODO: Populate arranged inputs
        UserDetails userDetails = null;

        // Act
        String actualGenerateRefreshTokenResult = this.jwtService.generateRefreshToken(userDetails);

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link JwtService#isTokenExpired(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testIsTokenExpired() {
        // TODO: Complete this test.
        //   Reason: R033 Missing Spring properties.
        //   Failed to create Spring context due to unresolvable @Value
        //   properties: java.lang.Long com.example.paydaytrade.service.impl.security.impl.JwtService.accessTokenExpiration
        //   Please check that at least one of the property files is provided
        //   and contains required variables:
        //   - application-test.properties (file missing)
        //   See https://diff.blue/R033 to resolve this issue.

        // Arrange
        // TODO: Populate arranged inputs
        String token = "";

        // Act
        boolean actualIsTokenExpiredResult = this.jwtService.isTokenExpired(token);

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link JwtService#isTokenValid(String, UserDetails)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testIsTokenValid() {
        // TODO: Complete this test.
        //   Reason: R033 Missing Spring properties.
        //   Failed to create Spring context due to unresolvable @Value
        //   properties: java.lang.Long com.example.paydaytrade.service.impl.security.impl.JwtService.accessTokenExpiration
        //   Please check that at least one of the property files is provided
        //   and contains required variables:
        //   - application-test.properties (file missing)
        //   See https://diff.blue/R033 to resolve this issue.

        // Arrange
        // TODO: Populate arranged inputs
        String token = "";
        UserDetails userDetails = null;

        // Act
        boolean actualIsTokenValidResult = this.jwtService.isTokenValid(token, userDetails);

        // Assert
        // TODO: Add assertions on result
    }
}

