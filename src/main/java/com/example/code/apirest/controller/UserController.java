package com.example.code.apirest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.code.apirest.dto.UserRequest;
import com.example.code.apirest.dto.UserResponse;
import com.example.code.apirest.mapper.UserConverter;
import com.example.code.application.usecase.UserCreationUseCase;
import com.example.code.application.usecase.UserModificationUseCase;
import com.example.code.application.usecase.UserRetrieveUseCase;
import com.example.code.domain.model.User;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

  @Autowired private UserCreationUseCase userCreationUseCase;
  @Autowired private UserModificationUseCase userModificationUseCase;
  @Autowired private UserRetrieveUseCase userRetrieveUseCase;

  @PostMapping
  public ResponseEntity<UserResponse> createUser(@Valid @RequestBody final UserRequest user) {
    log.info("Processing POST request with body {} ", user);

    final User newUser = userCreationUseCase.createUser(UserConverter.fromRequest(user));

    return ResponseEntity.status(HttpStatus.CREATED).body(UserConverter.fromDto(newUser));
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserResponse> getUserById(@PathVariable final Integer id) {
    log.info("Processing GET request by id {} ", id);

    final User newUser = userRetrieveUseCase.getUserById(id);

    return ResponseEntity.status(HttpStatus.OK).body(UserConverter.fromDto(newUser));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> modifyUser(
      @PathVariable final Integer id, @Valid @RequestBody final UserRequest user) {
    log.info("Processing PUT request with body {} ", user);

    userModificationUseCase.modifyUser(id, UserConverter.fromRequest(user));

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
