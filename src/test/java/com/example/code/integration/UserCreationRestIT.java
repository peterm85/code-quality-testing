package com.example.code.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.example.code.Application;
import com.example.code.apirest.dto.UserRequest;
import com.example.code.apirest.dto.UserResponse;

import java.net.URI;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = {Application.class})
@ActiveProfiles("integration-test")
public class UserCreationRestIT {

  @Autowired protected TestRestTemplate testRestTemplate;

  @LocalServerPort private int port;

  protected String getServiceUrl() {
    return "http://localhost:%d".formatted(this.port);
  }

  @Test
  @DisplayName(
      "GIVEN a valid user request "
          + "WHEN create user is called "
          + "THEN a response with 201 is returned with user data created")
  void createUserTest() throws URISyntaxException {
    // Given
    final String url = this.getServiceUrl();
    final UserRequest request =
        UserRequest.builder()
            .name("John")
            .surname("Snow")
            .address("Main Street 4th")
            .city("Invernalia")
            .email("johnsnow@got.com")
            .build();

    // When
    final var requestEntity = RequestEntity.post(new URI(url)).body(request);

    final ResponseEntity<UserResponse> response =
        this.testRestTemplate.exchange("/user", HttpMethod.POST, requestEntity, UserResponse.class);

    // Then
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().getId()).isNotNull();
  }
}
