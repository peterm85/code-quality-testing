package com.example.code.application.usecase;

import com.example.code.application.dto.UserModificationDto;

public interface UserModificationUseCase {

  void modifyUser(final int userId, final UserModificationDto userModificationDto);
}
