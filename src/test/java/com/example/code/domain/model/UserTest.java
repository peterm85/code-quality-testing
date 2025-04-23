package com.example.code.domain.model;

import static com.example.code.utils.UserGenerator.createUserCreationDto;
import static com.example.code.utils.UserGenerator.createUserModificationDto;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.code.application.dto.UserModificationDto;
import com.example.code.utils.UserGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class UserTest {

  @Test
  void createUser() {
    // Given
    final var userCreationDto = createUserCreationDto();
    // When
    final var user = User.create(userCreationDto);
    // Then
    assertThat(user).isNotNull();
    assertThat(user.getName()).isEqualTo(userCreationDto.getName());
    assertThat(user.getSurname()).isEqualTo(userCreationDto.getSurname());
    assertThat(user.getAddress()).isEqualTo(userCreationDto.getAddress());
    assertThat(user.getCity()).isEqualTo(userCreationDto.getCity());
    assertThat(user.getEmail()).isEqualTo(userCreationDto.getEmail());
  }

  @Test
  void modifyUser() {
    // Given
    final var userModificationDto = createUserModificationDto();
    final var user = UserGenerator.createUser();
    // When
    user.modify(userModificationDto);
    // Then
    assertThat(user).isNotNull();
    assertThat(user.getName()).isEqualTo(userModificationDto.getName());
    assertThat(user.getSurname()).isEqualTo(userModificationDto.getSurname());
    assertThat(user.getAddress()).isEqualTo(userModificationDto.getAddress());
    assertThat(user.getCity()).isEqualTo(userModificationDto.getCity());
    assertThat(user.getEmail()).isEqualTo(userModificationDto.getEmail());
  }

  static Stream<UserModificationDto> providePartialModificationDtos() {
    return Stream.of(
            UserModificationDto.builder().name("NewName").build(),
            UserModificationDto.builder().surname("NewSurname").build(),
            UserModificationDto.builder().address("NewAddress").build(),
            UserModificationDto.builder().city("NewCity").build(),
            UserModificationDto.builder().email("NewEmail").build()
    );
  }

  @ParameterizedTest
  @MethodSource("providePartialModificationDtos")
  void modifyUserWithSingleAttribute(UserModificationDto modificationDto) {
    // Given
    final var user = UserGenerator.createUser();
    // When
    user.modify(modificationDto);
    // Then
    checkUserAttributes(user, modificationDto);
  }

  private void checkUserAttributes(User user, UserModificationDto modificationDto) {
    if (modificationDto.getName() != null) {
      assertThat(user.getName()).isEqualTo(modificationDto.getName());
    }
    if (modificationDto.getSurname() != null) {
      assertThat(user.getSurname()).isEqualTo(modificationDto.getSurname());
    }
    if (modificationDto.getAddress() != null) {
      assertThat(user.getAddress()).isEqualTo(modificationDto.getAddress());
    }
    if (modificationDto.getCity() != null) {
      assertThat(user.getCity()).isEqualTo(modificationDto.getCity());
    }
    if (modificationDto.getEmail() != null) {
      assertThat(user.getEmail()).isEqualTo(modificationDto.getEmail());
    }
  }
}
