package com.example.code.apirest.handler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.example.code.domain.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserRestExceptionHandlerTest {

  @InjectMocks private UserRestExceptionHandler userRestExceptionHandler;

  @Test
  void handleUserNotFoundException() {
    // Given
    final int userId = 1;
    final var exception = new UserNotFoundException(userId);

    // When
    final var response =
        this.userRestExceptionHandler.handleUserNotFoundExceptions(exception, null);

    // Then
    assertThat(response.getStatusCode()).isEqualTo(NOT_FOUND);
    assertThat(response.getBody()).isNull();
  }

  @Test
  void handleAllExceptions() {
    // Given
    final var exception = new NullPointerException();

    // When
    final var response = this.userRestExceptionHandler.handleAllExceptions(exception, null);

    // Then
    assertThat(response.getStatusCode()).isEqualTo(INTERNAL_SERVER_ERROR);
    assertThat(response.getBody()).isNull();
  }
}
