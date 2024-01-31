package com.example.code.application.usecase.impl;

import com.example.code.application.usecase.UserCreationUseCase;
import com.example.code.domain.model.User;
import com.example.code.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserCreationUseCaseImpl implements UserCreationUseCase {

  @Autowired private UserRepository userRepository;

  @Override
  public User createUser(final User newUser) {

    return userRepository.createUser(newUser);
  }
}
