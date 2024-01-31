package com.example.code.application.usecase.impl;

import com.example.code.application.usecase.UserModificationUseCase;
import com.example.code.domain.model.User;
import com.example.code.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserModificationUseCaseImpl implements UserModificationUseCase {

  private UserRepository userRepository;

  public UserModificationUseCaseImpl(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public void modifyUser(final int id, final User user) {

    final User currentUser = userRepository.findUserById(id);

    currentUser.setName(user.getName());
    currentUser.setSurname(user.getSurname());
    currentUser.setAddress(user.getAddress());
    currentUser.setCity(user.getCity());
    currentUser.setEmail(user.getEmail());

    userRepository.modifyUser(currentUser);
  }
}
