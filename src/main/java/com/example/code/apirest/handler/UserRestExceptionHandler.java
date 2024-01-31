package com.example.code.apirest.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.example.code.domain.exception.UserNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class UserRestExceptionHandler {

  @ExceptionHandler(UserNotFoundException.class)
  public final ResponseEntity<Object> handleUserNotFoundExceptions(
      final UserNotFoundException ex, final WebRequest request) {
    log.error(
        "Response to {} with status {} and body '{}'",
        request,
        HttpStatus.NOT_FOUND,
        ex.getMessage());
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Object> handleAllExceptions(
      final Exception ex, final WebRequest request) {
    log.error(
        "Response to {} with status {} and body {}",
        request,
        HttpStatus.INTERNAL_SERVER_ERROR,
        ex.getMessage());
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
