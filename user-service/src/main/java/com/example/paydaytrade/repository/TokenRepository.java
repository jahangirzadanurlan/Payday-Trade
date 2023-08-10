package com.example.paydaytrade.repository;

import com.example.paydaytrade.model.entity.Token;
import com.example.paydaytrade.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {
    Optional<Token> findTokenByToken(String token);
    Optional<Token> findTokenByUser(User user);
}
