package com.example.code.repository;

import static com.example.code.utils.UserGenerator.createUser;
import static com.example.code.utils.UserGenerator.createUserEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.code.domain.exception.UserNotFoundException;
import com.example.code.domain.model.User;
import com.example.code.repository.entity.UserEntity;
import com.example.code.repository.mapper.UserEntityMapper;
import com.example.code.repository.mapper.UserEntityMapperImpl;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(classes = UserEntityMapperImpl.class)
class UserRepositoryImplTest {

  private UserRepositoryImpl userRepository;

  @Mock private UserJpaRepository userJpaRepository;

  @Autowired private UserEntityMapper userEntityMapper;

  @BeforeEach
  void settingUp() {
    this.userRepository = new UserRepositoryImpl(this.userJpaRepository, this.userEntityMapper);
  }

  private static Stream<Arguments> getUserByIdTestScenarios() {
    return Stream.of(
        Arguments.of("When user is found", 1, false),
        Arguments.of("When user is not found", 2, true));
  }

  @ParameterizedTest(name = "{index} {0}")
  @MethodSource("getUserByIdTestScenarios")
  void findUserByIdTest(final String testName, final int userId, final boolean throwAnException) {
    // Given
    final UserEntity mockedUserEntity = createUserEntity();

    // When
    when(this.userJpaRepository.findById(anyInt()))
        .thenAnswer(
            args -> {
              if (1 == (Integer) args.getArgument(0)) {
                return Optional.of(mockedUserEntity);
              } else {
                return Optional.ofNullable(null);
              }
            });

    // Then
    if (throwAnException) {
      assertThrows(UserNotFoundException.class, () -> this.userRepository.findUserById(userId));
    } else {
      final User user = this.userRepository.findUserById(userId);

      assertThat(user).isNotNull();
      verify(this.userJpaRepository).findById(anyInt());
    }
  }

  @Test
  void createUserTest() {
    // Given
    final User user = createUser();
    final UserEntity userEntity = createUserEntity();

    // When
    when(this.userJpaRepository.save(any(UserEntity.class))).thenReturn(userEntity);

    final User userResult = this.userRepository.createUser(user);

    // Then
    assertThat(userResult).isNotNull();
    verify(this.userJpaRepository).save(any(UserEntity.class));
  }

  @Test
  void modifyUserTest() {
    // Given
    final User user = createUser();
    final UserEntity userEntity = createUserEntity();

    // When
    when(this.userJpaRepository.save(any(UserEntity.class))).thenReturn(userEntity);

    this.userRepository.modifyUser(user);

    // Then
    verify(this.userJpaRepository).save(any(UserEntity.class));
  }
}
