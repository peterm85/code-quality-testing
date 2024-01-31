package com.example.code.utils;

import org.instancio.Instancio;

import com.example.code.apirest.dto.UserRequest;
import com.example.code.apirest.dto.UserResponse;
import com.example.code.domain.model.User;
import com.example.code.repository.entity.UserEntity;

public class UserGenerator {

  public static User createUser() {
    return Instancio.create(User.class);
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
