package com.example.code.application.usecase.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.code.application.usecase.UserModificationUseCase;
import com.example.code.domain.model.User;
import com.example.code.repository.UserRepository;

@Component
public class UserModificationUseCaseImpl implements UserModificationUseCase {

  @Autowired private UserRepository userRepository;

  @Override
  public void modifyUser(final int id, final User user) {

    final User currentUser = userRepository.getUserById(id);

    currentUser.setName(user.getName());
    currentUser.setSurname(user.getSurname());
    currentUser.setAddress(user.getAddress());
    currentUser.setCity(user.getCity());
    currentUser.setEmail(user.getEmail());

    userRepository.modifyUser(currentUser);
  }
}
