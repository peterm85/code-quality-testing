package com.example.code.apirest.mapper;

import com.example.code.apirest.dto.UserRequest;
import com.example.code.apirest.dto.UserResponse;
import com.example.code.domain.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

  User toUser(UserRequest request);

  UserResponse toResponse(User user);
}
