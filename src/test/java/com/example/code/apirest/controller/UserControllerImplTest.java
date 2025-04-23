package com.example.code.apirest.controller;

import static com.example.code.utils.UserGenerator.createUserRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;

import com.example.code.apirest.dto.UserResponse;
import com.example.code.application.dto.UserModificationDto;
import com.example.code.application.usecase.UserModificationUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = UserControllerTestConfig.class)
class UserControllerImplTest {

  private ApplicationContextRunner runner;

  @BeforeEach
  void setUp() {
    this.runner =
        new ApplicationContextRunner().withUserConfiguration(UserControllerTestConfig.class);
  }

  @Test
  void testContextOk() {
    this.runner.run(context -> assertThat(context).hasSingleBean(UserControllerTestConfig.class));
  }

  @Test
  void testGetUserById() {
    // Given
    final int userId = 1;

    // When then
    this.runner.run(
        context -> {
          final UserController api = context.getBean(UserController.class);
          final ResponseEntity<UserResponse> response = api.getUserById(userId);
          assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
          assertThat(response.getBody()).isNotNull();
        });
  }

  @Test
  void testCreateUser() {
    // Given
    final var userRequest = createUserRequest();

    // When then
    this.runner.run(
        context -> {
          final UserController api = context.getBean(UserController.class);
          final ResponseEntity<UserResponse> response = api.createUser(userRequest);
          assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
          assertThat(response.getBody()).isNotNull();
        });
  }

  @Test
  void testModifyUser() {
    // Given
    final int userId = 1;
    final var userRequest = createUserRequest();

    // When then
    this.runner.run(
        context -> {
          final UserController api = context.getBean(UserController.class);
          final UserModificationUseCase useCase = context.getBean(UserModificationUseCase.class);
          final ResponseEntity<Void> response = api.modifyUser(userId, userRequest);
          assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
          assertThat(response.getBody()).isNull();
          verify(useCase).modifyUser(anyInt(), any(UserModificationDto.class));
        });
  }
}
