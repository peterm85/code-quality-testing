package com.example.code.repository.mapper;

import com.example.code.domain.model.User;
import com.example.code.repository.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface UserEntityMapper {

  UserEntity toEntity(User user);

  User toUser(UserEntity entity);
}
