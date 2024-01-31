package com.example.code.repository;

import com.example.code.domain.exception.UserNotFoundException;
import com.example.code.domain.model.User;
import com.example.code.repository.entity.UserEntity;
import com.example.code.repository.mapper.UserEntityMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

  private UserJpaRepository userJpaRepository;

  private UserEntityMapper userEntityMapper;

  public UserRepositoryImpl(
      final UserJpaRepository userJpaRepository, final UserEntityMapper userEntityMapper) {
    this.userJpaRepository = userJpaRepository;
    this.userEntityMapper = userEntityMapper;
  }

  @Override
  public User createUser(final User user) {

    final UserEntity userEntity = userJpaRepository.save(userEntityMapper.toEntity(user));

    return userEntityMapper.toUser(userEntity);
  }

  @Override
  public User findUserById(final int userId) {

    return userJpaRepository
        .findById(userId)
        .map(u -> userEntityMapper.toUser(u))
        .orElseThrow(() -> new UserNotFoundException(userId));
  }

  @Override
  public void modifyUser(final User user) {

    userJpaRepository.save(userEntityMapper.toEntity(user));
  }
}
