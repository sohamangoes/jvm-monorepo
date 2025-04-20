package com.github.sohamangoes.helloworldapp.api;

import com.github.sohamangoes.helloworldapp.config.GreetingsConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(GreetingsApi.class)
@Import({
        GreetingsConfig.class
})
class GreetingsApiTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void shouldSayHello() {
        webTestClient.get().uri("/api/v1/greetings")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                .expectBody(String.class).isEqualTo("Hello World!");
    }

    @Test
    void shouldSayHelloToName() {
        webTestClient.get().uri("/api/v1/greetings/{name}", "Soham")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                .expectBody(String.class).isEqualTo("Hello Soham!");
    }
}
