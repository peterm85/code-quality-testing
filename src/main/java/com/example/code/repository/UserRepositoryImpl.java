package com.example.code.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.code.domain.exception.NotFoundException;
import com.example.code.domain.exception.NotFoundException.Errors;
import com.example.code.domain.model.User;
import com.example.code.repository.entity.UserEntity;
import com.example.code.repository.mapper.UserConverter;

@Repository
public class UserRepositoryImpl implements UserRepository {

  @Autowired private UserJpaRepository userJpaRepository;

  @Override
  public User createUser(final User user) {

    final UserEntity userEntity = userJpaRepository.save(UserConverter.fromDto(user));

    return UserConverter.fromEntity(userEntity);
  }

  @Override
  public User getUserById(final int userId) {

    return userJpaRepository
        .findById(userId)
        .map(u -> UserConverter.fromEntity(u))
        .orElseThrow(() -> new NotFoundException("getUserById", Errors.USER_NOT_FOUND, null));
  }

  @Override
  public void modifyUser(final User user) {

    userJpaRepository.save(UserConverter.fromDto(user));
  }
}
