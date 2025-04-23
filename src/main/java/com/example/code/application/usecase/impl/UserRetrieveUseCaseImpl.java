package com.example.code.application.usecase.impl;

import com.example.code.application.dto.UserDto;
import com.example.code.application.mapper.UserDtoMapper;
import com.example.code.application.usecase.UserRetrieveUseCase;
import com.example.code.domain.model.User;
import com.example.code.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserRetrieveUseCaseImpl implements UserRetrieveUseCase {

  private final UserRepository userRepository;

  private final UserDtoMapper userDtoMapper;

  @Override
  public UserDto getUserById(final Integer id) {
    final User user = userRepository.findUserById(id);
    return userDtoMapper.toUserDto(user);
  }
}
