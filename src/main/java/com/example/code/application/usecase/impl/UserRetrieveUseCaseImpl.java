package com.example.code.application.usecase.impl;

import com.example.code.application.usecase.UserRetrieveUseCase;
import com.example.code.domain.model.User;
import com.example.code.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRetrieveUseCaseImpl implements UserRetrieveUseCase {

  @Autowired private UserRepository userRepository;

  @Override
  public User getUserById(final Integer id) {
    return userRepository.findUserById(id);
  }
}
