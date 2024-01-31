package com.example.code.repository.mapper;

import org.mapstruct.Mapper;

import com.example.code.domain.model.User;
import com.example.code.repository.entity.UserEntity;

@Mapper
public interface UserEntityMapper {

  UserEntity toEntity(User user);

  User toUser(UserEntity entity);
}
