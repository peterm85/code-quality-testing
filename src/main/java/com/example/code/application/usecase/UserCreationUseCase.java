package com.example.code.application.usecase;

import com.example.code.domain.model.User;

public interface UserCreationUseCase {

  User createUser(final User newUser);
}
