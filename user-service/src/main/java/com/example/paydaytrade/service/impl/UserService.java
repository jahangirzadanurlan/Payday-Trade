package com.example.paydaytrade.service.impl;

import com.example.paydaytrade.exception.ApplicationException;
import com.example.paydaytrade.model.dto.request.DepositRequestDto;
import com.example.paydaytrade.model.dto.response.ResponseDto;
import com.example.paydaytrade.model.entity.User;
import com.example.paydaytrade.model.enums.Exceptions;
import com.example.paydaytrade.repository.UserRepository;
import com.example.paydaytrade.service.IUserService;
import com.example.paydaytrade.service.IWalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final IWalletService walletService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<ResponseDto> depositMoney(DepositRequestDto depositRequestDto) {
        Optional<User> user = userRepository.findUserByUsernameOrEmail(depositRequestDto.getUsername());

        if (passwordEncoder.matches(depositRequestDto.getPassword(),user.orElseThrow(() -> new ApplicationException(Exceptions.USER_NOT_FOUND_EXCEPTION)).getPassword())){
            walletService.increaseBalance(depositRequestDto.getMoney(),user.orElseThrow());
            return ResponseEntity.ok().body(new ResponseDto(depositRequestDto.getMoney() + " was added to " + depositRequestDto.getUsername() + " balance"));
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDto("User Password is wrong! "));
        }
    }

}
