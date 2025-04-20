package com.github.sohamangoes.helloworldapp.config;

import com.github.sohamangoes.helloworld.GreetingsService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class GreetingsConfig {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public GreetingsService greetingsService() {
        return new GreetingsService();
    }
}
