package com.example.paydaytrade.repository;

import com.example.paydaytrade.model.entity.Role;
import com.example.paydaytrade.model.entity.Token;
import com.example.paydaytrade.model.entity.User;
import com.example.paydaytrade.model.enums.RoleType;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {UserRepository.class})
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.example.paydaytrade.model.entity"})
@DataJpaTest(properties = {"spring.main.allow-bean-definition-overriding=true"})
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @Disabled("TODO: Complete this test")
    void testFindUserByUsernameOrEmail() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setPassword("iloveyou");

        Role role = new Role();
        role.setId(1L);
        role.setName(RoleType.USER);
        role.setUsers(new ArrayList<>());
        user.setRole(role);

        Token token = new Token();
        token.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        token.setExpired(true);
        token.setId(1L);
        token.setRevoked(true);
        token.setToken("ABC123");

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setId(1L);
        user2.setPassword("iloveyou");

        Role role2 = new Role();
        role2.setId(1L);
        role2.setName(RoleType.USER);
        role2.setUsers(new ArrayList<>());
        user2.setRole(role2);

        Token token2 = new Token();
        token2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        token2.setExpired(true);
        token2.setId(1L);
        token2.setRevoked(true);
        token2.setToken("ABC123");

        User user3 = new User();
        user3.setEmail("jane.doe@example.org");
        user3.setEnabled(true);
        user3.setId(1L);
        user3.setPassword("iloveyou");
        user3.setRole(new Role());
        user3.setToken(new Token());
        user3.setUserStock(new ArrayList<>());
        user3.setUsername("janedoe");
        user3.setWallet(1);
        token2.setUser(user3);
        user2.setToken(token2);
        user2.setUserStock(new ArrayList<>());
        user2.setUsername("janedoe");
        user2.setWallet(1);
        token.setUser(user2);
        user.setToken(token);
        user.setUserStock(new ArrayList<>());
        user.setUsername("janedoe");
        user.setWallet(1);
        userRepository.save(user);

        User user4 = new User();
        user4.setEmail("john.smith@example.org");
        user4.setEnabled(false);
        user4.setPassword("Password");

        Role role3 = new Role();
        role3.setId(2L);
        role3.setName(RoleType.ADMIN);
        role3.setUsers(new ArrayList<>());
        user4.setRole(role3);

        Token token3 = new Token();
        token3.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        token3.setExpired(false);
        token3.setId(2L);
        token3.setRevoked(false);
        token3.setToken("Token");

        User user5 = new User();
        user5.setEmail("john.smith@example.org");
        user5.setEnabled(false);
        user5.setId(2L);
        user5.setPassword("Password");

        Role role4 = new Role();
        role4.setId(2L);
        role4.setName(RoleType.ADMIN);
        role4.setUsers(new ArrayList<>());
        user5.setRole(role4);

        Token token4 = new Token();
        token4.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        token4.setExpired(false);
        token4.setId(2L);
        token4.setRevoked(false);
        token4.setToken("Token");

        User user6 = new User();
        user6.setEmail("john.smith@example.org");
        user6.setEnabled(false);
        user6.setId(2L);
        user6.setPassword("Password");
        user6.setRole(new Role());
        user6.setToken(new Token());
        user6.setUserStock(new ArrayList<>());
        user6.setUsername("Username");
        user6.setWallet(0);
        token4.setUser(user6);
        user5.setToken(token4);
        user5.setUserStock(new ArrayList<>());
        user5.setUsername("Username");
        user5.setWallet(0);
        token3.setUser(user5);
        user4.setToken(token3);
        user4.setUserStock(new ArrayList<>());
        user4.setUsername("Username");
        user4.setWallet(0);
        userRepository.save(user4);
        userRepository.findUserByUsernameOrEmail("janedoe");
    }
}

