package com.example.code.application.usecase;

import com.example.code.application.dto.UserDto;

public interface UserRetrieveUseCase {

  UserDto getUserById(final Integer id);
}
