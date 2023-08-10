package com.example.paydaytrade.repository;

import com.example.paydaytrade.model.entity.Role;
import com.example.paydaytrade.model.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findRoleByName(RoleType roleType);
}
