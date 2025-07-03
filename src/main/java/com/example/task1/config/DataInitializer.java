package com.example.task1.config;

import com.example.task1.model.Expense;
import com.example.task1.model.User;
import com.example.task1.repository.ExpenseRepository;
import com.example.task1.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.math.BigDecimal;

@Configuration
public class DataInitializer {
    @Bean
    public CommandLineRunner loadData(ExpenseRepository repository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return (args) -> {
            // Add default user
            if (userRepository.findByUsername("user") == null) {
                userRepository.save(new User("user", passwordEncoder.encode("password")));
            }
        };
    }
} 