# Simple Expense Tracker

A clean and simple Spring Boot application for managing personal expenses with basic CRUD operations, built using Java, Spring Boot, JPA, and a modern HTML/CSS/JavaScript frontend.

## Features

- **Basic CRUD Operations**: Create, Read, and Delete expenses
- **Simple Web Interface**: Clean, responsive frontend
- **H2 Database**: In-memory database for development and testing
- **RESTful API**: Clean and intuitive REST endpoints
- **Data Validation**: Input validation with proper error handling

## Technology Stack

### Backend
- **Java 17**
- **Spring Boot 3.5.3**
- **Spring Data JPA**
- **H2 Database**

### Frontend
- **HTML5**
- **CSS3** (with modern features like CSS Grid, Flexbox, and animations)
- **Vanilla JavaScript** (ES6+)

## Project Structure

```
src/main/java/com/example/task1/
├── Task1Application.java          # Main application class
├── model/
│   └── Expense.java              # Expense entity with JPA annotations
├── repository/
│   └── ExpenseRepository.java    # JPA repository interface
├── service/
│   └── ExpenseService.java       # Business logic layer
├── controller/
│   └── ExpenseController.java    # REST controller
├── exception/
│   └── GlobalExceptionHandler.java # Global exception handler
└── config/
    └── DataInitializer.java      # Database initializer

src/main/resources/
├── static/
│   ├── index.html               # Main frontend page
│   ├── styles.css               # CSS styles
│   └── script.js                # JavaScript functionality
└── application.properties       # Application configuration
```

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

### Running the Application

1. **Clone or download the project**

2. **Navigate to the project directory**
   ```bash
   cd task1
   ```

3. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```
   or
   ```bash
   mvn spring-boot:run
   ```

4. **Access the application**
   - **Web Interface**: http://localhost:8080
   - **H2 Console**: http://localhost:8080/h2-console
     - JDBC URL: `jdbc:h2:mem:expensedb`
     - Username: `sa`
     - Password: `password`

## Frontend Features

### 🎨 **Clean UI Design**
- Simple, modern interface
- Responsive design that works on desktop, tablet, and mobile
- Smooth animations and transitions

### ➕ **Easy Expense Management**
- Simple form to add new expenses
- One-click delete with confirmation
- Auto-populated date fields

### 📱 **Mobile Responsive**
- Optimized for all screen sizes
- Touch-friendly interface

## API Endpoints

### Basic CRUD Operations

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/expenses` | Create a new expense |
| GET | `/api/expenses` | Get all expenses |
| DELETE | `/api/expenses/{id}` | Delete an expense |
| DELETE | `/api/expenses` | Delete all expenses |

## Frontend Usage

### Adding an Expense
1. Fill out the "Add New Expense" form at the top
2. Enter description, amount, and date
3. Click "Add Expense" - the expense appears immediately in the list

### Deleting an Expense
1. Click the "Show Expenses" button to view all expenses
2. Click the "Delete" button next to any expense
3. Confirm deletion in the popup dialog
4. The expense is removed from the list

### Deleting All Expenses
1. Click the "Delete All" button
2. Confirm deletion in the popup dialog
3. All expenses are removed from the list

## Request/Response Examples

### Create an Expense

**POST** `/api/expenses`

```json
{
  "description": "Grocery shopping",
  "amount": 85.50,
  "expenseDate": "2024-01-15T00:00:00"
}
```

**Response:**
```json
{
  "id": 1,
  "description": "Grocery shopping",
  "amount": 85.50,
  "expenseDate": "2024-01-15T00:00:00"
}
```

### Get All Expenses

**GET** `/api/expenses`

**Response (empty database):**
```json
[]
```

## Architecture Overview

The application follows a **Layered Architecture** pattern:

1. **Presentation Layer** (`controller/`) - Handles HTTP requests/responses
2. **Business Logic Layer** (`service/`) - Contains business rules and logic
3. **Data Access Layer** (`repository/`) - Manages database operations
4. **Data Model Layer** (`model/`) - Defines data structures
5. **Configuration Layer** (`config/`) - Application configuration
6. **Exception Handling Layer** (`exception/`) - Error management

This structure provides:
- **Separation of Concerns**: Each layer has a specific responsibility
- **Maintainability**: Easy to modify and extend
- **Testability**: Each layer can be tested independently
- **Simplicity**: Clean and focused codebase

## Database Schema

The application uses a simple `expenses` table with the following structure:

```sql
CREATE TABLE expenses (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    expense_date TIMESTAMP NOT NULL
);
```

## Starting Fresh

The application starts with a completely empty database. You can:
- Add your own expenses through the web interface
- Use the H2 console to inspect the database structure
- Test all CRUD operations with your own data

This is a clean, simple expense tracker perfect for learning Spring Boot concepts and basic web development! 

## Running with Docker

You can build and run the application in a Docker container:

1. **Build the Docker image**
   ```bash
   docker build -t expense-tracker .
   ```
2. **Run the Docker container**
   ```bash
   docker run -p 8080:8080 expense-tracker
   ```

- The app will be available at http://localhost:8080
- The H2 console will be available at http://localhost:8080/h2-console 