package com.example.code.domain.model;

import static com.example.code.utils.UserGenerator.createUserCreationDto;
import static com.example.code.utils.UserGenerator.createUserModificationDto;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.code.utils.UserGenerator;
import org.junit.jupiter.api.Test;

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
}
