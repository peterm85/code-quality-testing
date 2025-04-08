package com.example.code.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_CLASS;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_CLASS;

import com.example.code.apirest.dto.UserRequest;
import com.example.code.apirest.dto.UserResponse;
import java.net.URI;
import java.net.URISyntaxException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

@Sql(value = "/scripts/import_users.sql", executionPhase = BEFORE_TEST_CLASS)
@Sql(value = "/scripts/cleanup_data.sql", executionPhase = AFTER_TEST_CLASS)
class UserModificationRestIT extends AbstractRestIT {

  @Test
  @DisplayName(
      "GIVEN a valid user request "
          + "WHEN modify user is called "
          + "THEN a response with 204 is returned with user data created")
  void modifyUserTest() throws URISyntaxException {
    // Given
    final String url = this.getServiceUrl();

    final int userId = 1;
    final UserRequest request =
        UserRequest.builder()
            .name("John")
            .surname("Snow")
            .address("Main Street 4th")
            .city("Invernalia")
            .email("johnsnow@got.com")
            .build();

    // When
    final var requestEntity = RequestEntity.put(new URI(url)).body(request);

    final ResponseEntity<UserResponse> response =
        this.testRestTemplate.exchange(
            "/user/{userId}", HttpMethod.PUT, requestEntity, UserResponse.class, userId);

    // Then
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    assertThat(response.getBody()).isNull();
  }
}
