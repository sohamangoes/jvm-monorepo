package com.github.sohamangoes.helloworldapp.api;

import com.github.sohamangoes.helloworld.GreetingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "api/v1/greetings", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class GreetingsApi {

  private final GreetingsService greetingsService;

  @GetMapping
  public Mono<String> handleGreeting() {
    return Mono.just(greetingsService.greet());
  }

  @GetMapping("{name}")
  public Mono<String> handleGreeting(@PathVariable String name) {
    return Mono.just(greetingsService.greet(name));
  }
}
