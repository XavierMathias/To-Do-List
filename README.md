# Task Manager Application

A JavaFX-based Task Manager application that allows users to create, update, delete, and manage tasks with a user-friendly graphical interface.

## Features

- Add tasks with a title, status, and selection checkbox.
- Edit task titles inline with a text field.
- Change task status using a drop-down combo box (enum-based status values).
- Select tasks using a checkbox for bulk actions.
- Delete tasks and update database records in real-time.
- Task data is stored in a database and automatically fetched when the application loads.
- Responsive TableView with constrained column resizing for better user experience.

## Prerequisites

To run this project, ensure you have the following installed:

- **Java 17** or later
- **JavaFX SDK 17** or later
- **Maven 3.6+** for dependency management
- **MySQL 8.x** or any compatible relational database system

Ensure the database is set up with appropriate tables. Example schema:

```sql
CREATE TABLE tasks (
    name VARCHAR(255) NOT NULL,
    is_selected BOOLEAN DEFAULT FALSE,
    status ENUM('PENDING', 'IN_PROGRESS', 'COMPLETED') NOT NULL
);
```

## How to Run
1. Clone the repository
   ```bash
   git clone https://github.com/yourusername/task-manager-javafx.git
   cd task-manager-javafx
   ```
2. Build the project using Maven:
   ```bash
   mvn clean install
   ```
3. Run the application from your IDE or command line:
   ```bash
   mvn javafx:run
   ```
