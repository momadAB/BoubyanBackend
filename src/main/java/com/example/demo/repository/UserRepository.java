package com.example.demo.repository;

import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // Find by username, ignoring case
    Optional<UserEntity> findByUsernameIgnoreCase(String username);
    // Find by account_id
    Optional<UserEntity> findByAccountEntity(Long id);
    // Find UserEntity by AccountEntity's accountNumber
    Optional<UserEntity> findByAccountEntity_AccountNumber(String accountNumber);
    boolean existsByUsernameIgnoreCase(String username);
}
