package com.example.code.application.usecase;

import com.example.code.application.dto.UserCreationDto;
import com.example.code.application.dto.UserDto;

public interface UserCreationUseCase {

  UserDto createUser(final UserCreationDto userCreationDto);
}
