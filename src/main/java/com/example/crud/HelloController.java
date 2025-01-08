package com.example.crud;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.time.LocalDate;

public class HelloController {

    @FXML
    private Label customText;
    @FXML
    private TextField textField;
    @FXML
    private TableView<Tasks> tableView;
    @FXML
    private TableColumn<Tasks, String> taskTitleColumn;
    @FXML
    private TableColumn<Tasks, TaskStatus> taskStatusColumn;
    @FXML
    private TableColumn<Tasks, String> taskDescriptionColumn;
    @FXML
    private TableColumn<Tasks, Integer> tasksIntegerTableColumn;



    private ObservableList<Tasks> tasksList = FXCollections.observableArrayList(
            new Tasks(1, "Task 1", TaskStatus.PENDING),
            new Tasks(2, "Random task", TaskStatus.COMPLETED)
    );

    @FXML
    private void initialize() {
        tableView.setEditable(true); // Enable editing on the Tableview

        // Creating columns for the tables
        taskTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        taskStatusColumn.setCellValueFactory(new PropertyValueFactory<>("taskStatus"));
        taskDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        tasksIntegerTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        // Using TextFieldTableCell for the task title column
        taskTitleColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        // Handle edit commit for the task title
        taskTitleColumn.setOnEditCommit(event -> {
            Tasks task = event.getRowValue();
            task.setTitle(event.getNewValue()); // Update the task's title
        });

        // Set items for the TableView
        tableView.setItems(tasksList);
    }

    @FXML
    protected void onHelloButtonClick() {

        customText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void addTaskButtonClick(){
        Tasks task = new Tasks(tasksList.size()+1, textField.getText(), TaskStatus.PENDING);
        System.out.println(tasksList.add(task) ? "Task as been added" : "No task has been added");
        tableView.setItems(tasksList);
    }
    @FXML
    protected void deleteTaskButtonClick(){
        customText.setText("I can delete tasks");
    }
    @FXML
    protected void editTaskButtonClick(){
        customText.setText("I can edit tasks");
    }
    @FXML
    protected void markTaskButtonClick(){
        customText.setText("I can mark tasks");
    }



}