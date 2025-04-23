package com.example.code.application.usecase.impl;

import com.example.code.application.dto.UserModificationDto;
import com.example.code.application.usecase.UserModificationUseCase;
import com.example.code.domain.model.User;
import com.example.code.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserModificationUseCaseImpl implements UserModificationUseCase {

  private final UserRepository userRepository;

  @Override
  public void modifyUser(final int id, final UserModificationDto userModificationDto) {

    final User currentUser = userRepository.findUserById(id);
    currentUser.modify(userModificationDto);

    userRepository.modifyUser(currentUser);
  }
}
