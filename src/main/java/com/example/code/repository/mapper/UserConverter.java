package com.example.code.repository.mapper;

import com.example.code.domain.model.User;
import com.example.code.repository.entity.UserEntity;

public class UserConverter {

  public static UserEntity fromDto(final User dto) {
    return UserEntity.builder()
        .id(dto.getId())
        .name(dto.getName())
        .surname(dto.getSurname())
        .address(dto.getAddress())
        .city(dto.getCity())
        .email(dto.getEmail())
        .build();
  }

  public static User fromEntity(final UserEntity entity) {
    return User.builder()
        .id(entity.getId())
        .name(entity.getName())
        .surname(entity.getSurname())
        .address(entity.getAddress())
        .city(entity.getCity())
        .email(entity.getEmail())
        .build();
  }
}
