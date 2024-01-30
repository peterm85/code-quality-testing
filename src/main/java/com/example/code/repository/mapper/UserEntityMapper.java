package com.example.code.repository.mapper;

import com.example.code.domain.model.User;
import com.example.code.repository.entity.UserEntity;

public interface UserEntityMapper {

  UserEntity toEntity(User user);

  User toUser(UserEntity entity);
}
