package com.example.code.apirest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {

  private Integer id;

  private String name;

  private String surname;

  private String address;

  private String city;

  private String email;
}
