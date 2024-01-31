package com.example.code.application.usecase.impl;

import com.example.code.application.usecase.UserCreationUseCase;
import com.example.code.domain.model.User;
import com.example.code.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserCreationUseCaseImpl implements UserCreationUseCase {

  private final UserRepository userRepository;

  public UserCreationUseCaseImpl(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User createUser(final User newUser) {

    return userRepository.createUser(newUser);
  }
}
