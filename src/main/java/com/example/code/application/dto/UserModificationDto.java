package com.example.code.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserModificationDto {

  private String name;

  private String surname;

  private String address;

  private String city;

  private String email;
}
