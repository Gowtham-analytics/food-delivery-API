package com.fooddelivery.fooddeliveryapi.mappers;

import com.fooddelivery.fooddeliveryapi.domain.dto.create.UserCreateDto;
import com.fooddelivery.fooddeliveryapi.domain.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserCreateDto toCreateDto(UserEntity userEntity) {
        return new UserCreateDto(
                userEntity.getUsername(),
                userEntity.getPassword(),
                null
        );
    }

    public UserEntity fromCreateUser(UserCreateDto userCreateDto) {
        return new UserEntity(
                null,
                userCreateDto.username(),
                null,
                userCreateDto.password(),
                null,
                null,
                null,
                null
        );
    }
}
