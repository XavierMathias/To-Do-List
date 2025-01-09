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
    public static void insertTask(int id, String name, boolean selected, TaskStatus status){
        String sql = "INSERT INTO tasks(id, name, is_selected, status) VALUES (?,?,?,?::task_status)"; // cast to task_status


        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            // Check if taskID exists before proceeding
            if(taskIDExist(id)){
                System.out.println("This taskId " + id + " already exists");
            } else {
                pstmt.setInt(1, id);
                pstmt.setString(2, name);
                pstmt.setBoolean(3, selected);
                pstmt.setString(4, status.name());

                pstmt.executeUpdate(); // Proceed with insert if ID doesn't exist
                System.out.println("Added taskId" + id + " to database");
            }


        } catch (SQLException e){
            e.printStackTrace();
        }

    } // end of method

    public static boolean taskIDExist(int id){
        System.out.println("Checking if task exists");
        String sql = "SELECT EXISTS (SELECT 1 FROM tasks WHERE id = ?)";

        try(Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                return rs.getBoolean(1); // returns true if record exists, false otherwise
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;


    }




} // end of class
