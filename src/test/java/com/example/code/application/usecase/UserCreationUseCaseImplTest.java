package com.example.code.application.usecase;

import static com.example.code.utils.UserGenerator.createUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.code.application.usecase.impl.UserCreationUseCaseImpl;
import com.example.code.domain.model.User;
import com.example.code.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UserCreationUseCaseImplTest {

  @InjectMocks private UserCreationUseCaseImpl userCreationUseCaseImpl;

  @Mock private UserRepository userRepository;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void createUserTest() {
    // Given
    final User user = createUser();

    // When
    when(userRepository.createUser(any(User.class))).thenReturn(user);

    final User resultUser = userCreationUseCaseImpl.createUser(user);

    // Then
    verify(userRepository).createUser(any(User.class));
    assertThat(resultUser).isNotNull();
  }
}
