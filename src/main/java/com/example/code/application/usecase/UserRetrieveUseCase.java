package com.example.code.application.usecase;

import com.example.code.domain.model.User;

public interface UserRetrieveUseCase {

  User getUserById(final Integer id);
}
