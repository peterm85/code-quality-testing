package com.example.code.apirest.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
public class UserRequest {

  @NotEmpty(message = "User name is mandatory")
  private String name;

  @NotEmpty(message = "User surname is mandatory")
  private String surname;

  @NotEmpty(message = "Address is mandatory")
  @Size(
      min = 5,
      max = 30,
      message = "Address must be higher than 5 characteres and lower than 30 characters")
  private String address;

  @NotEmpty(message = "City name is mandatory")
  @Size(max = 20, message = "City name must be lower than 20 characters")
  private String city;

  @NotEmpty(message = "Email is mandatory")
  @Email(message = "Email is formatted incorrectly")
  private String email;
}
