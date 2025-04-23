package com.example.code.application.usecase;

import static com.example.code.utils.UserGenerator.createUser;
import static com.example.code.utils.UserGenerator.createUserDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.code.application.dto.UserDto;
import com.example.code.application.mapper.UserDtoMapper;
import com.example.code.application.usecase.impl.UserRetrieveUseCaseImpl;
import com.example.code.domain.model.User;
import com.example.code.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserRetrieveUseCaseImplTest {

  @InjectMocks private UserRetrieveUseCaseImpl userRetrieveUseCaseImpl;

  @Mock private UserRepository userRepository;

  @Mock private UserDtoMapper userDtoMapper;

  @Test
  void getUserByIdTest() {
    // Given
    final int userId = 1;
    final User mockedUser = createUser();
    final UserDto mockedUserDto = createUserDto();

    // When
    when(userRepository.findUserById(anyInt())).thenReturn(mockedUser);
    when(userDtoMapper.toUserDto(any(User.class))).thenReturn(mockedUserDto);

    final UserDto resultUser = userRetrieveUseCaseImpl.getUserById(userId);

    // Then
    verify(userRepository).findUserById(anyInt());
    assertThat(resultUser).isNotNull();
  }
}
