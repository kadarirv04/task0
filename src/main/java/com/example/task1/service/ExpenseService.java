package com.example.task1.service;

import com.example.task1.model.Expense;
import com.example.task1.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    
    @Autowired
    private ExpenseRepository expenseRepository;
    
    // Create a new expense
    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }
    
    // Get all expenses
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }
    
    // Delete all expenses
    public void deleteAllExpenses() {
        expenseRepository.deleteAll();
    }
    
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }
} 