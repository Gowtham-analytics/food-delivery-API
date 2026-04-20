package com.fooddelivery.fooddeliveryapi.mappers;

import com.fooddelivery.fooddeliveryapi.domain.dto.create.UserCreateDto;
import com.fooddelivery.fooddeliveryapi.domain.dto.request.UserLoginRequestDto;
import com.fooddelivery.fooddeliveryapi.domain.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserCreateDto toCreateDto(UserEntity userEntity) {
        return new UserCreateDto(
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getRole()
        );
    }

    public UserEntity fromCreateUser(UserCreateDto userCreateDto) {
        return new UserEntity(
                null,
                userCreateDto.username(),
                null,
                userCreateDto.password(),
                userCreateDto.role(),
                null,
                null,
                null
        );
    }

    public UserLoginRequestDto toLoginRequestDto(UserEntity userEntity) {
        return new UserLoginRequestDto(
                userEntity.getUsername(),
                userEntity.getPassword(),
                null
        );
    }

    public UserEntity fromLoginRequestUser(UserLoginRequestDto userLoginRequestDto) {
        return new UserEntity(
                null,
                userLoginRequestDto.username(),
                null,
                userLoginRequestDto.password(),
                null,
                null,
                null,
                null
        );
    }
}
