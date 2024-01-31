package com.example.code.repository;

import com.example.code.domain.model.User;

public interface UserRepository {

  User createUser(User user);

  User findUserById(int userId);

  void modifyUser(User user);
}
