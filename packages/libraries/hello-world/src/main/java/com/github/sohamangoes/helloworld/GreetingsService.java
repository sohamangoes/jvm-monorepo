package com.github.sohamangoes.helloworld;

public class GreetingsService {

  public String greet(String name) {
    return "Hello " + name + "!";
  }

  public String greet() {
    return greet("World");
  }
}
