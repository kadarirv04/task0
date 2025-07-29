package com.example.task1.controller;

import com.example.task1.model.Expense;
import com.example.task1.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @PostMapping
    public Expense addExpense(@RequestBody Expense expense) {
        if (expense.getExpenseDate() == null) {
            expense.setExpenseDate(java.time.LocalDateTime.now());
        }
        return expenseService.addExpense(expense);
    }

    @DeleteMapping
    public void deleteAllExpenses() {
        expenseService.deleteAllExpenses();
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
    }
} 