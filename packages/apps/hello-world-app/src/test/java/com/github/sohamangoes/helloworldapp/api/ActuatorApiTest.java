package com.github.sohamangoes.helloworldapp.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient
class ActuatorApiTest {

  @Autowired WebTestClient webTestClient;

  @Test
  void healthCheckShouldWork() {
    webTestClient.get().uri("/actuator/health").exchange().expectStatus().isOk();
  }

  @Test
  void metricsShouldWork() {
    webTestClient.get().uri("/actuator/metrics").exchange().expectStatus().isOk();
  }
}
