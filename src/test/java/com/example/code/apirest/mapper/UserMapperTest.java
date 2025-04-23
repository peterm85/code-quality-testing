package com.example.code.apirest.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.code.apirest.dto.UserRequest;
import com.example.code.application.dto.UserDto;
import com.example.code.utils.JsonTestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = UserMapperImpl.class)
class UserMapperTest extends JsonTestUtils {

  private static final String PATH = "apirest/mapper/";

  @Autowired private UserMapper userMapper;

  @Test
  void convertUserRequestToUserCreationDto() {
    // Given
    final var userRequest = readFile(PATH, "user-request.json", UserRequest.class);
    // When
    final var userCreationDto = userMapper.toUserCreationDto(userRequest);
    // Then
    checkResult(userCreationDto, PATH, "user-dto.json");
  }

  @Test
  void convertUserRequestToUserModificationDto() {
    // Given
    final var userRequest = readFile(PATH, "user-request.json", UserRequest.class);
    // When
    final var userModificationDto = userMapper.toUserModificationDto(userRequest);
    // Then
    checkResult(userModificationDto, PATH, "user-dto.json");
  }

  @Test
  void convertUserDtoToUserResponse() {
    // Given
    final var userDto = readFile(PATH, "user.json", UserDto.class);
    // When
    final var userResponse = userMapper.toResponse(userDto);
    // Then
    checkResult(userResponse, PATH, "user-response.json");
  }

  @Test
  void convertUserRequestToUserWhenNull() {
    // Given
    final UserRequest userRequest = null;
    // When
    final var userCreationDto = userMapper.toUserCreationDto(userRequest);
    // Then
    assertThat(userCreationDto).isNull();
  }

  @Test
  void convertUserToUserResponseWhenNull() {
    // Given
    final UserDto userDto = null;
    // When
    final var userResponse = userMapper.toResponse(userDto);
    // Then
    assertThat(userResponse).isNull();
  }
}
