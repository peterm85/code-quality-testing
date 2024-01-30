package com.example.code.apirest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

  private Integer id;

  private String name;

  private String surname;

  private String address;

  private String city;

  private String email;
}
