package com.example.task1.config;

import com.example.task1.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    private final ExpenseRepository expenseRepository;
    
    @Autowired
    public DataInitializer(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }
    
    @Override
    public void run(String... args) throws Exception {
        // Clear any existing data and start with empty database
        expenseRepository.deleteAll();
        
        System.out.println("Database initialized with empty state!");
    }
} 