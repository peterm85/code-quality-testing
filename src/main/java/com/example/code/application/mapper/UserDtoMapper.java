package com.example.code.application.mapper;

import com.example.code.application.dto.UserDto;
import com.example.code.domain.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserDtoMapper {
  UserDto toUserDto(User user);
}
