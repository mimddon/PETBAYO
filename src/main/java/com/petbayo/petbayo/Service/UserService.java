package com.petbayo.petbayo.Service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.petbayo.petbayo")
public class UserService {
    public void register(User user) {
    }
}