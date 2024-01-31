package com.example.code.apirest.controller;

import static com.example.code.utils.UserGenerator.createUser;
import static com.example.code.utils.UserGenerator.createUserResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.code.apirest.controller.impl.UserControllerImpl;
import com.example.code.apirest.dto.UserRequest;
import com.example.code.apirest.mapper.UserMapper;
import com.example.code.application.usecase.UserCreationUseCase;
import com.example.code.application.usecase.UserModificationUseCase;
import com.example.code.application.usecase.UserRetrieveUseCase;
import com.example.code.domain.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class UserControllerTestConfig {

  @Bean
  public UserController productSamplesApi(
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

    when(userCreationUseCase.createUser(any(User.class))).thenReturn(createUser());

    return userCreationUseCase;
  }

  @Bean
  public UserModificationUseCase userModifyUseCase() {
    final UserModificationUseCase userModificationUseCase = mock(UserModificationUseCase.class);

    doNothing().when(userModificationUseCase).modifyUser(anyInt(), any(User.class));

    return userModificationUseCase;
  }

  @Bean
  public UserRetrieveUseCase userRetrieveUseCase() {

    final UserRetrieveUseCase userRetrieveUseCase = mock(UserRetrieveUseCase.class);

    when(userRetrieveUseCase.getUserById(anyInt())).thenReturn(createUser());

    return userRetrieveUseCase;
  }

  @Bean
  public UserMapper userMapper() {
    final UserMapper userMapper = mock(UserMapper.class);

    when(userMapper.toResponse(any(User.class))).thenReturn(createUserResponse());
    when(userMapper.toUser(any(UserRequest.class))).thenReturn(createUser());

    return userMapper;
  }
}
