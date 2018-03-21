package com.example.medics.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataConfiguration {
    @Bean
    public UserService userService(){
        return new UserService();
    }

}