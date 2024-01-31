package com.example.code.apirest.controller;

import com.example.code.apirest.dto.UserRequest;
import com.example.code.apirest.dto.UserResponse;
import org.springframework.http.ResponseEntity;

public interface UserController {

  public ResponseEntity<UserResponse> createUser(final UserRequest user);

  public ResponseEntity<UserResponse> getUserById(final Integer id);

  public ResponseEntity<Void> modifyUser(final Integer id, final UserRequest user);
}
