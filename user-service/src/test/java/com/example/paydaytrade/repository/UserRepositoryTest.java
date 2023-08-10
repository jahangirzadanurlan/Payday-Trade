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

    /**
     * Method under test: {@link UserRepository#findUserByUsernameOrEmail(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindUserByUsernameOrEmail() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   org.springframework.dao.InvalidDataAccessApiUsageException: detached entity passed to persist: com.example.paydaytrade.model.entity.Token; nested exception is org.hibernate.PersistentObjectException: detached entity passed to persist: com.example.paydaytrade.model.entity.Token
        //       at com.sun.proxy.$Proxy146.save(Unknown Source)
        //   org.hibernate.PersistentObjectException: detached entity passed to persist: com.example.paydaytrade.model.entity.Token
        //       at org.hibernate.event.internal.DefaultPersistEventListener.onPersist(DefaultPersistEventListener.java:120)
        //       at org.hibernate.event.service.internal.EventListenerGroupImpl.fireEventOnEachListener(EventListenerGroupImpl.java:118)
        //       at org.hibernate.internal.SessionImpl.firePersist(SessionImpl.java:780)
        //       at org.hibernate.internal.SessionImpl.persist(SessionImpl.java:748)
        //       at org.hibernate.engine.spi.CascadingActions$7.cascade(CascadingActions.java:298)
        //       at org.hibernate.engine.internal.Cascade.cascadeToOne(Cascade.java:510)
        //       at org.hibernate.engine.internal.Cascade.cascadeAssociation(Cascade.java:434)
        //       at org.hibernate.engine.internal.Cascade.cascadeProperty(Cascade.java:220)
        //       at org.hibernate.engine.internal.Cascade.cascade(Cascade.java:153)
        //       at org.hibernate.event.internal.AbstractSaveEventListener.cascadeBeforeSave(AbstractSaveEventListener.java:426)
        //       at org.hibernate.event.internal.AbstractSaveEventListener.performSaveOrReplicate(AbstractSaveEventListener.java:263)
        //       at org.hibernate.event.internal.AbstractSaveEventListener.performSave(AbstractSaveEventListener.java:192)
        //       at org.hibernate.event.internal.AbstractSaveEventListener.saveWithGeneratedId(AbstractSaveEventListener.java:122)
        //       at org.hibernate.event.internal.DefaultPersistEventListener.entityIsTransient(DefaultPersistEventListener.java:185)
        //       at org.hibernate.event.internal.DefaultPersistEventListener.onPersist(DefaultPersistEventListener.java:128)
        //       at org.hibernate.event.internal.DefaultPersistEventListener.onPersist(DefaultPersistEventListener.java:55)
        //       at org.hibernate.event.service.internal.EventListenerGroupImpl.fireEventOnEachListener(EventListenerGroupImpl.java:107)
        //       at org.hibernate.internal.SessionImpl.firePersist(SessionImpl.java:756)
        //       at org.hibernate.internal.SessionImpl.persist(SessionImpl.java:742)
        //       at com.sun.proxy.$Proxy142.persist(Unknown Source)
        //       at com.sun.proxy.$Proxy146.save(Unknown Source)
        //   See https://diff.blue/R013 to resolve this issue.

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

