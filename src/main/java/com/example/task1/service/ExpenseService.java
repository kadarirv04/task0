package com.example.task1.service;

import com.example.task1.model.Expense;
import com.example.task1.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    
    private final ExpenseRepository expenseRepository;
    
    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }
    
    // Create a new expense
    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }
    
    // Get all expenses
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }
    
    // Delete an expense
    public boolean deleteExpense(Long id) {
        if (expenseRepository.existsById(id)) {
            expenseRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    // Delete all expenses
    public void deleteAllExpenses() {
        expenseRepository.deleteAll();
    }
} 