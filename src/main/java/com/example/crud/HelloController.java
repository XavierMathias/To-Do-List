package com.example.crud;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.time.LocalDate;

import static com.example.crud.TaskStatus.*;

public class HelloController {

    @FXML
    private TextField textField;
    @FXML
    private TableView<Tasks> tableView;
    @FXML
    private TableColumn<Tasks, String> taskTitleColumn;
    @FXML
    private TableColumn<Tasks, TaskStatus> taskStatusColumn;
    @FXML
    private TableColumn<Tasks, Boolean> taskSelectedColumn;
    @FXML
    private TableColumn<Tasks, Integer> tasksIntegerTableColumn;

    private CheckBox selectTask;


    private ObservableList<Tasks> tasksList = FXCollections.observableArrayList(
            new Tasks(1, "Task 1", PENDING),
            new Tasks(2, "Random task", COMPLETED)
    );

    @FXML
    private void initialize() {
        tableView.setEditable(true); // Enable editing on the Tableview

        // Creating columns for the tables
        taskTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        taskSelectedColumn.setCellValueFactory(new PropertyValueFactory<>("selected"));
        taskStatusColumn.setCellValueFactory(new PropertyValueFactory<>("taskStatus"));
        tasksIntegerTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        taskSelectedColumn.setCellFactory(CheckBoxTableCell.forTableColumn(taskSelectedColumn));
        taskSelectedColumn.setEditable(true);


        // Using TextFieldTableCell for the task title column
        taskTitleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        taskStatusColumn.setCellFactory(ComboBoxTableCell.forTableColumn(
                FXCollections.observableArrayList( TaskStatus.values()) // Using enum constants
        ));

        // Handle edit commit for the task title
        taskTitleColumn.setOnEditCommit(event -> {
            Tasks task = event.getRowValue();
            task.setTitle(event.getNewValue()); // Update the task's title cell
        });

        //Handle edit commit for the task status
        taskStatusColumn.setOnEditCommit(event -> {
            Tasks task = event.getRowValue();
            task.setTaskStatus(event.getNewValue());
        });

        // Set items for the TableView
        tableView.setItems(tasksList);
    }

    @FXML
    protected void addTaskButtonClick(){
        Tasks task = new Tasks(tasksList.size()+1, textField.getText(), PENDING);
        System.out.println(tasksList.add(task) ? "Task as been added" : "No task has been added");
        textField.clear();
        tableView.setItems(tasksList);
    }
    @FXML
    protected void deleteTaskButtonClick(){

    }
    @FXML
    protected void markTaskButtonClick(){

    }



}