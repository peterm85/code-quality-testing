package com.example.code.apirest.controller.impl;

import com.example.code.apirest.controller.UserController;
import com.example.code.apirest.dto.UserRequest;
import com.example.code.apirest.dto.UserResponse;
import com.example.code.apirest.mapper.UserMapper;
import com.example.code.application.dto.UserDto;
import com.example.code.application.usecase.UserCreationUseCase;
import com.example.code.application.usecase.UserModificationUseCase;
import com.example.code.application.usecase.UserRetrieveUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserControllerImpl implements UserController {

  private final UserCreationUseCase userCreationUseCase;
  private final UserModificationUseCase userModificationUseCase;
  private final UserRetrieveUseCase userRetrieveUseCase;
  private final UserMapper userMapper;

  @Override
  @PostMapping
  public ResponseEntity<UserResponse> createUser(@Valid @RequestBody final UserRequest user) {
    log.info("Processing POST request with body {} ", user);

    final UserDto newUser = userCreationUseCase.createUser(userMapper.toUserCreationDto(user));

    return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toResponse(newUser));
  }

  @Override
  @GetMapping("/{id}")
  public ResponseEntity<UserResponse> getUserById(@PathVariable final Integer id) {
    log.info("Processing GET request by id {} ", id);

    final UserDto userDto = userRetrieveUseCase.getUserById(id);

    return ResponseEntity.status(HttpStatus.OK).body(userMapper.toResponse(userDto));
  }

  @Override
  @PutMapping("/{id}")
  public ResponseEntity<Void> modifyUser(
      @PathVariable final Integer id, @Valid @RequestBody final UserRequest user) {
    log.info("Processing PUT request with body {} ", user);

    userModificationUseCase.modifyUser(id, userMapper.toUserModificationDto(user));

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
