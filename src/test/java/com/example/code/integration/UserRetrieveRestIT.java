package com.example.code.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_CLASS;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_CLASS;

import com.example.code.apirest.dto.UserResponse;
import java.net.URISyntaxException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

@Sql(value = "/scripts/import_users.sql", executionPhase = BEFORE_TEST_CLASS)
@Sql(value = "/scripts/cleanup_data.sql", executionPhase = AFTER_TEST_CLASS)
public class UserRetrieveRestIT extends AbstractRestIT {

  @Test
  @DisplayName(
      "GIVEN a valid userId "
          + "WHEN get user by id is called "
          + "THEN a response with 200 is returned with user data")
  void getUserByIdTest() throws URISyntaxException {
    // Given
    final int userId = 1;

    // When
    final ResponseEntity<UserResponse> response =
        this.testRestTemplate.exchange(
            "/user/{userId}", HttpMethod.GET, null, UserResponse.class, userId);

    // Then
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
  }
}
