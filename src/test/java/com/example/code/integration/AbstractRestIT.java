package com.example.code.integration;

import com.example.code.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = {Application.class})
@ActiveProfiles("integration-test")
public abstract class AbstractRestIT {

  @Autowired protected TestRestTemplate testRestTemplate;

  @LocalServerPort private int port;

  protected String getServiceUrl() {
    return "http://localhost:%d".formatted(this.port);
  }
}
