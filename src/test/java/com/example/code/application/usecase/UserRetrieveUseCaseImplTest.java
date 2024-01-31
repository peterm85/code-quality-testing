package com.example.code.application.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.code.application.usecase.impl.UserRetrieveUseCaseImpl;
import com.example.code.domain.model.User;
import com.example.code.repository.UserRepository;

import static com.example.code.utils.UserGenerator.createUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserRetrieveUseCaseImplTest {

  @InjectMocks private UserRetrieveUseCaseImpl userRetrieveUseCaseImpl;

  @Mock private UserRepository userRepository;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getUserByIdTest() {
    // Given
    final int userId = 1;
    final User mockedUser = createUser();

    // When
    when(userRepository.getUserById(anyInt())).thenReturn(mockedUser);

    final User resultUser = userRetrieveUseCaseImpl.getUserById(userId);

    // Then
    verify(userRepository).getUserById(anyInt());
    assertThat(resultUser).isNotNull();
  }
}
