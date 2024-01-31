package com.example.code.apirest.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.code.apirest.dto.UserRequest;
import com.example.code.domain.model.User;
import com.example.code.utils.JsonTestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = UserMapperImpl.class)
class UserMapperTest extends JsonTestUtils {

  private static final String PATH = "apirest/mapper/";

  @Autowired private UserMapper userMapper;

  @Test
  void convertUserRequestToUser() {
    // Given
    final var userRequest = readFile(PATH, "user-request.json", UserRequest.class);
    // When
    final var user = userMapper.toUser(userRequest);
    // Then
    checkResult(user, PATH, "user.json");
  }

  @Test
  void convertUserToUserResponse() {
    // Given
    final var user = readFile(PATH, "user.json", User.class);
    // When
    final var userResponse = userMapper.toResponse(user);
    // Then
    checkResult(userResponse, PATH, "user-response.json");
  }

  @Test
  void convertUserRequestToUserWhenNull() {
    // Given
    final UserRequest userRequest = null;
    // When
    final var user = userMapper.toUser(userRequest);
    // Then
    assertThat(user).isNull();
  }

  @Test
  void convertUserToUserResponseWhenNull() {
    // Given
    final User user = null;
    // When
    final var userResponse = userMapper.toResponse(user);
    // Then
    assertThat(userResponse).isNull();
  }
}
