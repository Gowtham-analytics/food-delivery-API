package com.fooddelivery.fooddeliveryapi.repository;

import com.fooddelivery.fooddeliveryapi.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Boolean existsByUsername(String username);
    Optional<UserEntity> findByUsernameAndIsActive(String username, Boolean isActive);
}
