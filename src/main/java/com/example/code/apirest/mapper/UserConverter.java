package com.example.code.apirest.mapper;

import com.example.code.apirest.dto.UserRequest;
import com.example.code.apirest.dto.UserResponse;
import com.example.code.domain.model.User;

public class UserConverter {

  public static UserResponse fromDto(final User domain) {
    return UserResponse.builder()
        .id(domain.getId())
        .name(domain.getName())
        .surname(domain.getSurname())
        .address(domain.getAddress())
        .city(domain.getCity())
        .email(domain.getEmail())
        .build();
  }

  public static User fromRequest(final UserRequest request) {
    return User.builder()
        .name(request.getName())
        .surname(request.getSurname())
        .address(request.getAddress())
        .city(request.getCity())
        .email(request.getEmail())
        .build();
  }
}
