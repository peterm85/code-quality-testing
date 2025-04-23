package com.example.code.domain.model;

import com.example.code.application.dto.UserCreationDto;
import com.example.code.application.dto.UserModificationDto;
import java.io.Serial;
import java.io.Serializable;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

  @Serial private static final long serialVersionUID = 6855827588272130730L;

  private Integer id;

  private String name;

  private String surname;

  private String address;

  private String city;

  private String email;

  public static User create(UserCreationDto dto) {
    return User.builder()
        .name(dto.getName())
        .surname(dto.getSurname())
        .address(dto.getAddress())
        .city(dto.getCity())
        .email(dto.getEmail())
        .build();
  }

  public void modify(UserModificationDto dto) {
    this.name = dto.getName() != null ? dto.getName() : this.name;
    this.surname = dto.getSurname() != null ? dto.getSurname() : this.surname;
    this.address = dto.getAddress() != null ? dto.getAddress() : this.address;
    this.city = dto.getCity() != null ? dto.getCity() : this.city;
    this.email = dto.getEmail() != null ? dto.getEmail() : this.email;
  }
}
