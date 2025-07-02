// API Base URL
const API_BASE_URL = '/api/expenses';

// DOM Elements
const expenseForm = document.getElementById('expenseForm');
const showExpensesBtn = document.getElementById('showExpensesBtn');
const deleteAllBtn = document.getElementById('deleteAllBtn');
const expensesList = document.getElementById('expensesList');
const expensesContainer = document.getElementById('expensesContainer');

// Initialize the application
document.addEventListener('DOMContentLoaded', function() {
    console.log('Simple Expense Tracker loaded');
    
    // Set default date to today
    setDefaultDate();
    
    // Add event listeners
    expenseForm.addEventListener('submit', handleFormSubmit);
    showExpensesBtn.addEventListener('click', showExpenses);
    deleteAllBtn.addEventListener('click', deleteAllExpenses);
});

// Set default date to current date
function setDefaultDate() {
    const today = new Date().toISOString().split('T')[0];
    document.getElementById('expenseDate').value = today;
}

// Handle form submission
async function handleFormSubmit(event) {
    event.preventDefault();
    
    const formData = new FormData(event.target);
    const expenseData = {
        description: formData.get('description'),
        amount: parseFloat(formData.get('amount')),
        expenseDate: formData.get('expenseDate') + 'T00:00:00' // Add time to make it a valid datetime
    };
    
    try {
        const response = await fetch(API_BASE_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(expenseData)
        });
        
        if (!response.ok) {
            throw new Error('Failed to add expense');
        }
        
        const newExpense = await response.json();
        console.log('Expense added successfully:', newExpense);
        
        // Reset form
        event.target.reset();
        setDefaultDate();
        
        // Show success message
        alert('Expense added successfully!');
        
    } catch (error) {
        console.error('Error adding expense:', error);
        alert('Failed to add expense. Please try again.');
    }
}

// Show expenses
async function showExpenses() {
    try {
        const response = await fetch(API_BASE_URL);
        if (!response.ok) {
            throw new Error('Failed to load expenses');
        }
        
        const expenses = await response.json();
        displayExpenses(expenses);
        
    } catch (error) {
        console.error('Error loading expenses:', error);
        alert('Failed to load expenses. Please try again.');
    }
}

// Display expenses in the list
function displayExpenses(expenses) {
    expensesList.style.display = 'block';
    
    if (expenses.length === 0) {
        expensesContainer.innerHTML = '<p>No expenses found.</p>';
        return;
    }
    
    const expensesHTML = expenses.map(expense => {
        const date = new Date(expense.expenseDate).toLocaleDateString();
        return `
            <div>
                <h3>${expense.description}</h3>
                <p>$${expense.amount.toFixed(2)} - ${date}</p>
                <button onclick="deleteExpense(${expense.id})">Delete</button>
            </div>
        `;
    }).join('');
    
    expensesContainer.innerHTML = expensesHTML;
}

// Delete expense function
async function deleteExpense(expenseId) {
    if (!confirm('Are you sure you want to delete this expense?')) {
        return;
    }
    
    try {
        const response = await fetch(`${API_BASE_URL}/${expenseId}`, {
            method: 'DELETE'
        });
        
        if (!response.ok) {
            throw new Error('Failed to delete expense');
        }
        
        // Refresh the expenses list
        showExpenses();
        alert('Expense deleted successfully!');
        
    } catch (error) {
        console.error('Error deleting expense:', error);
        alert('Failed to delete expense. Please try again.');
    }
}

// Delete all expenses function
async function deleteAllExpenses() {
    if (!confirm('Are you sure you want to delete all expenses?')) {
        return;
    }
    
    try {
        const response = await fetch(API_BASE_URL, {
            method: 'DELETE'
        });
        
        if (!response.ok) {
            throw new Error('Failed to delete all expenses');
        }
        
        // Refresh the expenses list
        showExpenses();
        alert('All expenses deleted successfully!');
        
    } catch (error) {
        console.error('Error deleting all expenses:', error);
        alert('Failed to delete all expenses. Please try again.');
    }
} 