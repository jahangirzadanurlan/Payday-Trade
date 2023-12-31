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
import com.example.paydaytrade.service.impl.security.IAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;
    private final SecurityHelper securityHelper;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailSenderService mailSenderService;
    private final IConfirmationTokenService confirmationTokenService;


    @Override
    public AuthenticationResponse registration(RegistrationRequest request) {
        Role role=roleRepository.findRoleByName(RoleType.USER)//If there is such a role in the base, let it come, if not, throw an error
                        .orElseThrow(() -> new RuntimeException("Role not found! "));

        User user = getUser(request, role);

        String accessToken=jwtService.generateToken(user);
        String refreshToken=jwtService.generateRefreshToken(user);

        Token token = getToken(request, accessToken);
        tokenRepository.save(token);
        user.setToken(token);
        userRepository.save(user);

        return AuthenticationResponse.builder()
                .message("A confirmation email was sent to " + user.getEmail() + ". Please verify your account by clicking on the link ")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public AuthenticationResponse authentication(AuthenticationRequest request) {//This method defines the Login process
        log.info("Entered in authenticate method");
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        } catch (Exception e) {
            // Handle authentication failure
            log.error("Authentication failed: {}", e.getMessage());
            throw new RuntimeException("Authentication failed", e);
        }
        log.info("Method exited!");

        Optional<User> user = userRepository.findUserByUsernameOrEmail(request.getUsername());
        String accessToken=jwtService.generateToken(user.orElseThrow());
        String refreshToken=jwtService.generateRefreshToken(user.orElseThrow());

        /*After the token is changed, we must change the token in the database.
        If we don't, JwtAuthenticationFilter will throw an error.*/
        Token token=tokenRepository.findTokenByUser(user.orElseThrow())
                .orElseThrow(() -> new RuntimeException("Token doesn't exist: " + user));
        token.setToken(accessToken);
        tokenRepository.save(token);

        return AuthenticationResponse.builder()
                .message(user.orElseThrow().getEmail() + " login is successfully")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public AuthenticationResponse refreshToken(String authHeader) {
        if (!securityHelper.authHeaderIsValid(authHeader)){
            throw new RuntimeException();
        }

        String jwt=authHeader.substring(7);
        String username=jwtService.extractUsername(jwt);

        if (username != null){
            User user=userRepository.findUserByUsernameOrEmail(username)
                    .orElseThrow(() -> new RuntimeException("Username doesn't exist: " + username));
            Token token=tokenRepository.findTokenByUser(user)
                    .orElseThrow(() -> new RuntimeException("Token doesn't exist: " + user));

            if(jwtService.isTokenValid(jwt,user)){
                String accessToken=jwtService.generateToken(user);
                String refreshToken=jwtService.generateRefreshToken(user);

                /*After the token is refreshed, we must change the token in the database.
                If we don't, JwtAuthenticationFilter will throw an error.*/
                token.setToken(accessToken);
                tokenRepository.save(token);
                return AuthenticationResponse.builder()
                        .message(username + "refreshing is successfully")
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
            }

        }

        throw new RuntimeException("Token is invalid!");
    }

    private User getUser(RegistrationRequest request, Role role) {
        User user = User.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))//Password must be encoded
                .role(role)
                .build();
        userRepository.save(user);

        //Send Mail
        ConfirmationToken token = ConfirmationToken.builder()
                .token(UUID.randomUUID().toString())
                .user(user)
                .createdAt(LocalDateTime.now())
                .build();
        confirmationTokenService.save(token);
        mailSenderService.sendMail(request.getEmail(),token);
        return user;
    }

    private Token getToken(RegistrationRequest request, String accessToken) {
        //Since we will use the user in the token we will create, we get the user from the database
        Optional<User> _user = userRepository.findUserByUsernameOrEmail(request.getUsername());

        Token token = Token.builder()
                .token(accessToken)
                .revoked(false)
                .expired(false)
                .user(_user.orElseThrow())
                .createdAt(LocalDateTime.now())
                .build();
        return token;
    }

    public ResponseEntity<String> confirmAccount(UUID uuid){
        ConfirmationToken token = confirmationTokenService.getTokenByUUID(uuid.toString());
        if (token != null){
            User user = token.getUser();
            user.setEnabled(true);
            userRepository.save(user);

            token.setConfirmedAt(LocalDateTime.now());
            confirmationTokenService.save(token);
            return ResponseEntity.ok().body(user.getEmail() + " Confirmation is successfully");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Link is invalid");
        }

    }
}
