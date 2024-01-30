package com.example.code.apirest.handler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.code.domain.exception.NotFoundException;
import com.example.code.domain.exception.NotFoundException.Errors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ExtendWith(MockitoExtension.class)
public class UserRestExceptionHandlerTest {

  @InjectMocks private UserRestExceptionHandler userRestExceptionHandler;

  @Test
  void handleUserNotFoundException() {
    // Given
    final var exception = new NotFoundException("getUserById", Errors.USER_NOT_FOUND, null);

    // When
    final var response = this.userRestExceptionHandler.handleNotFoundExceptions(exception, null);

    // Then
    assertThat(response.getStatusCode()).isEqualTo(NOT_FOUND);
    assertThat(response.getBody()).isNull();
  }
}
