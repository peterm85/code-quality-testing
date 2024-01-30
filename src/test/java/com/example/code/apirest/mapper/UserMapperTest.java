package com.example.code.apirest.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.code.apirest.dto.UserRequest;
import com.example.code.domain.model.User;
import com.example.code.utils.BaseTest;

@SpringBootTest(classes = UserMapperImpl.class)
class UserMapperTest extends BaseTest {

  private static final String PATH = "apirest/mapper/";

  @Autowired private UserMapper userMapper;

  @Test
  void convertUserRequestToUser() {
    // Given
    final UserRequest userRequest = readFile(PATH, "user-request.json", UserRequest.class);
    // When
    final var user = userMapper.toUser(userRequest);
    // Then
    checkResult(user, PATH, "user.json");
  }

  @Test
  void convertUserToUserResponse() {
    // Given
    final User user = readFile(PATH, "user.json", User.class);
    // When
    final var userResponse = userMapper.toResponse(user);
    // Then
    checkResult(userResponse, PATH, "user-response.json");
  }
}
