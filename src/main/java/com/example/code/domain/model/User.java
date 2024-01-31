package com.example.code.domain.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable {

  private static final long serialVersionUID = 6855827588272130730L;

  private Integer id;

  private String name;

  private String surname;

  private String address;

  private String city;

  private String email;
}
