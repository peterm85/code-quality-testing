package com.example.code.apirest.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.code.apirest.dto.UserResponse;

import static com.example.code.apirest.UserGenerator.createUserRequest;
import static org.assertj.core.api.Assertions.assertThat;

public class UserControllerImplTest {

  private ApplicationContextRunner runner;

  @BeforeEach
  public void setUp() {
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
          final ResponseEntity<Void> response = api.modifyUser(userId, userRequest);
          assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
          assertThat(response.getBody()).isNull();
        });
  }
}
