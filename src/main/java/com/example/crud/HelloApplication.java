package com.example.crud;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        String sqlCheckType = """
        SELECT EXISTS (
            SELECT 1 
            FROM pg_type 
            WHERE typname = 'task_status'
        );
        """;

        try (Connection connection = DatabaseConnection.connect();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sqlCheckType)
             ){
            if (rs.next() && !rs.getBoolean(1)) {
                DatabaseConnection.createTaskStatus();  // Call your method to create the type
                DatabaseConnection.createTasksTable();
            }
            System.out.println("Database connected successfully!");

        } catch (SQLException e){
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
        }


        DatabaseConnection.createTasksTable();



        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600 , 500);
        stage.setTitle("To-Do List");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}