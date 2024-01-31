package com.example.code.repository.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.code.domain.model.User;
import com.example.code.repository.entity.UserEntity;
import com.example.code.utils.JsonTestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = UserEntityMapperImpl.class)
class UserEntityMapperTest extends JsonTestUtils {

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

  @Test
  void convertUserEntityToUserWhenNull() {
    // Given
    final UserEntity userEntity = null;
    // When
    final var user = userEntityMapper.toUser(userEntity);
    // Then
    assertThat(user).isNull();
  }

  @Test
  void convertUserToUserEntityWhenNull() {
    // Given
    final User user = null;
    // When
    final var userEntity = userEntityMapper.toEntity(user);
    // Then
    assertThat(userEntity).isNull();
  }
}
