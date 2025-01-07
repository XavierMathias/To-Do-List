package com.example.crud;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class HelloController {

    @FXML
    private Label customText;
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
        taskTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        taskStatusColumn.setCellValueFactory(new PropertyValueFactory<>("taskStatus"));
        taskDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        tasksIntegerTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableView.setItems(tasksList);
    }

    @FXML
    protected void onHelloButtonClick() {

        customText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void addTaskButtonClick(){
        customText.setText("I can add tasks");
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