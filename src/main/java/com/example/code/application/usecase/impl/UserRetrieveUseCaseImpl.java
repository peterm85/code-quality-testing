package com.example.code.application.usecase.impl;

import com.example.code.application.usecase.UserRetrieveUseCase;
import com.example.code.domain.model.User;
import com.example.code.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserRetrieveUseCaseImpl implements UserRetrieveUseCase {

  private UserRepository userRepository;

  public UserRetrieveUseCaseImpl(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User getUserById(final Integer id) {
    return userRepository.findUserById(id);
  }
}
