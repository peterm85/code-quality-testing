package com.example.code.domain.exception;

import java.io.Serial;

public class UserNotFoundException extends RuntimeException {

  @Serial private static final long serialVersionUID = -5895639208606544290L;

  public UserNotFoundException(final Integer id) {
    super(String.format("User with id [%s] not found.", id));
  }
}
