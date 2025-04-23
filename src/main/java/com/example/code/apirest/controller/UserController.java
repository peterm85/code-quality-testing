package com.example.code.apirest.controller;

import com.example.code.apirest.dto.UserRequest;
import com.example.code.apirest.dto.UserResponse;
import org.springframework.http.ResponseEntity;

public interface UserController {

  ResponseEntity<UserResponse> createUser(final UserRequest user);

  ResponseEntity<UserResponse> getUserById(final Integer id);

  ResponseEntity<Void> modifyUser(final Integer id, final UserRequest user);
}
