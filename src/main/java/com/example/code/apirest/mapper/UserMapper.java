package com.example.code.apirest.mapper;

import com.example.code.apirest.dto.UserRequest;
import com.example.code.apirest.dto.UserResponse;
import com.example.code.application.dto.UserCreationDto;
import com.example.code.application.dto.UserDto;
import com.example.code.application.dto.UserModificationDto;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

  UserCreationDto toUserCreationDto(UserRequest request);

  UserModificationDto toUserModificationDto(UserRequest request);

  UserResponse toResponse(UserDto userDto);
}
