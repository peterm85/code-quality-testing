package com.example.code.application.usecase;

import com.example.code.domain.model.User;

public interface UserModificationUseCase {

  void modifyUser(final int userId, final User user);
}
