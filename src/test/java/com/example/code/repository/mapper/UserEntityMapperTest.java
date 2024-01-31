package com.example.code.repository.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.code.domain.model.User;
import com.example.code.repository.entity.UserEntity;
import com.example.code.utils.BaseTest;

@SpringBootTest(classes = UserEntityMapperImpl.class)
public class UserEntityMapperTest extends BaseTest {

  private static final String PATH = "repository/mapper/";

  @Autowired private UserEntityMapper userEntityMapper;

  @Test
  void convertUserEntityToUser() {
    // Given
    final var userEntity = readFile(PATH, "user-entity.json", UserEntity.class);
    // When
    final var user = userEntityMapper.toUser(userEntity);
    // Then
    checkResult(user, PATH, "user.json");
  }

  @Test
  void convertUserToUserEntity() {
    // Given
    final var user = readFile(PATH, "user.json", User.class);
    // When
    final var userEntity = userEntityMapper.toEntity(user);
    // Then
    checkResult(userEntity, PATH, "user-entity.json");
  }
}
