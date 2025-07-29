package com.example.task1.repository;

import com.example.task1.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    // Only basic JPA methods are used: save(), findAll(), existsById(), deleteById(), deleteAll()
} 