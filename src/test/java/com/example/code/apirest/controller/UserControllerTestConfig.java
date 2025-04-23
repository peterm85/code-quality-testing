package com.example.code.apirest.controller;

import static com.example.code.utils.UserGenerator.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.code.apirest.controller.impl.UserControllerImpl;
import com.example.code.apirest.dto.UserRequest;
import com.example.code.apirest.mapper.UserMapper;
import com.example.code.application.dto.UserCreationDto;
import com.example.code.application.dto.UserDto;
import com.example.code.application.dto.UserModificationDto;
import com.example.code.application.usecase.UserCreationUseCase;
import com.example.code.application.usecase.UserModificationUseCase;
import com.example.code.application.usecase.UserRetrieveUseCase;
import org.springframework.context.annotation.Bean;

public class UserControllerTestConfig {

  @Bean
  public UserController userApi(
      final UserCreationUseCase userCreationUseCase,
      final UserModificationUseCase userModificationUseCase,
      final UserRetrieveUseCase userRetrieveUseCase,
      final UserMapper userMapper) {

    return new UserControllerImpl(
        userCreationUseCase, userModificationUseCase, userRetrieveUseCase, userMapper);
  }

  @Bean
  public UserCreationUseCase userCreationUseCase() {
    final UserCreationUseCase userCreationUseCase = mock(UserCreationUseCase.class);

    when(userCreationUseCase.createUser(any(UserCreationDto.class))).thenReturn(createUserDto());

    return userCreationUseCase;
  }

  @Bean
  public UserModificationUseCase userModificationUseCase() {
    final UserModificationUseCase userModificationUseCase = mock(UserModificationUseCase.class);

    doNothing().when(userModificationUseCase).modifyUser(anyInt(), any(UserModificationDto.class));

    return userModificationUseCase;
  }

  @Bean
  public UserRetrieveUseCase userRetrieveUseCase() {

    final UserRetrieveUseCase userRetrieveUseCase = mock(UserRetrieveUseCase.class);

    when(userRetrieveUseCase.getUserById(anyInt())).thenReturn(createUserDto());

    return userRetrieveUseCase;
  }

  @Bean
  public UserMapper userMapper() {
    final UserMapper userMapper = mock(UserMapper.class);

    when(userMapper.toResponse(any(UserDto.class))).thenReturn(createUserResponse());
    when(userMapper.toUserCreationDto(any(UserRequest.class))).thenReturn(createUserCreationDto());
    when(userMapper.toUserModificationDto(any(UserRequest.class)))
        .thenReturn(createUserModificationDto());

    return userMapper;
  }
}
