package com.example.crud;

import java.sql.*;

public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/cruddb";
    private static final String username = "davidsantos";
    private static final String password = "XavierMathias";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    // this method creates a connection with server, using the URL of the server, username and password
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, username, password);
    }

    public static void createTasksTable(){
        String sql = """
                CREATE TABLE IF NOT EXISTS tasks (
                id SERIAL PRIMARY KEY,
                name VARCHAR(100),
                is_selected BOOLEAN,
                status task_status
                );
                """; // creating the command for create a table if this table doesn't exist

        try (Connection conn = connect();
            Statement stmt = conn.createStatement()){ // creates the command statement
            stmt.execute(sql); // sends the command the server
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTaskStatus(){
        String sql = """
                CREATE TYPE task_status AS ENUM ('PENDING', 'IN_PROGRESS', 'COMPLETED');
                """;

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()){ // creates the command statement
            stmt.execute(sql); // sends the command the server
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // this method will INSERT a task
    public static void insertTask(String name, boolean selected){
        String sql = "INSERT INTO tasks(name, selected) VALUES (?,?)";

        try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, name);
            pstmt.setBoolean(2, selected);
            pstmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }

    }


} // end of class
