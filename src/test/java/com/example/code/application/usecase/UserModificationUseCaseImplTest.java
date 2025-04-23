package com.example.code.application.usecase;

import static com.example.code.utils.UserGenerator.createUser;
import static com.example.code.utils.UserGenerator.createUserModificationDto;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.code.application.dto.UserModificationDto;
import com.example.code.application.usecase.impl.UserModificationUseCaseImpl;
import com.example.code.domain.model.User;
import com.example.code.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserModificationUseCaseImplTest {

  @InjectMocks private UserModificationUseCaseImpl userModificationUseCaseImpl;

  @Mock private UserRepository userRepository;

  @Captor private ArgumentCaptor<User> userCaptor;

  @Test
  void modifyUserTest() {
    // Given
    final int userId = 1;
    final User mockedUser = createUser();
    final UserModificationDto modifiedUser = createUserModificationDto();

    // When
    when(userRepository.findUserById(anyInt())).thenReturn(mockedUser);
    doNothing().when(userRepository).modifyUser(any(User.class));

    userModificationUseCaseImpl.modifyUser(userId, modifiedUser);

    // Then
    verify(userRepository).findUserById(anyInt());
    verify(userRepository).modifyUser(this.userCaptor.capture());

    final User userCaptorValue = this.userCaptor.getValue();
    assertAll(
        "Assert user properties",
        () ->
            assertEquals(modifiedUser.getName(), userCaptorValue.getName(), "Name is not the same"),
        () ->
            assertEquals(
                modifiedUser.getSurname(), userCaptorValue.getSurname(), "Surname is not the same"),
        () ->
            assertEquals(
                modifiedUser.getAddress(), userCaptorValue.getAddress(), "Address is not the same"),
        () ->
            assertEquals(modifiedUser.getCity(), userCaptorValue.getCity(), "City is not the same"),
        () ->
            assertEquals(
                modifiedUser.getEmail(), userCaptorValue.getEmail(), "Email is not the same"));
  }
}
