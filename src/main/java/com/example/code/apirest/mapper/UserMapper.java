package com.example.code.apirest.mapper;

import org.mapstruct.Mapper;

import com.example.code.apirest.dto.UserRequest;
import com.example.code.apirest.dto.UserResponse;
import com.example.code.domain.model.User;

@Mapper
public interface UserMapper {

  User toUser(UserRequest request);

  UserResponse toResponse(User user);
}
