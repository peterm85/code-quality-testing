package com.example.code.application.usecase.impl;

import com.example.code.application.dto.UserCreationDto;
import com.example.code.application.dto.UserDto;
import com.example.code.application.mapper.UserDtoMapper;
import com.example.code.application.usecase.UserCreationUseCase;
import com.example.code.domain.model.User;
import com.example.code.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserCreationUseCaseImpl implements UserCreationUseCase {

  private final UserRepository userRepository;

  private final UserDtoMapper userDtoMapper;

  @Override
  public UserDto createUser(final UserCreationDto userCreationDto) {

    final User newUser = userRepository.createUser(User.create(userCreationDto));

    return userDtoMapper.toUserDto(newUser);
  }
}
