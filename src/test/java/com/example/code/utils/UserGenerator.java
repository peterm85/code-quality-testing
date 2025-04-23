package com.example.code.utils;

import com.example.code.apirest.dto.UserRequest;
import com.example.code.apirest.dto.UserResponse;
import com.example.code.application.dto.UserCreationDto;
import com.example.code.application.dto.UserDto;
import com.example.code.application.dto.UserModificationDto;
import com.example.code.domain.model.User;
import com.example.code.repository.entity.UserEntity;
import org.instancio.Instancio;

public class UserGenerator {

  public static User createUser() {
    return Instancio.create(User.class);
  }

  public static UserCreationDto createUserCreationDto() {
    return Instancio.create(UserCreationDto.class);
  }

  public static UserModificationDto createUserModificationDto() {
    return Instancio.create(UserModificationDto.class);
  }

  public static UserDto createUserDto() {
    return Instancio.create(UserDto.class);
  }

  public static UserResponse createUserResponse() {
    return Instancio.create(UserResponse.class);
  }

  public static UserRequest createUserRequest() {
    return Instancio.create(UserRequest.class);
  }

  public static UserEntity createUserEntity() {
    return Instancio.create(UserEntity.class);
  }
}
