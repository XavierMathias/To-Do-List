package com.example.crud.Controllers;

import com.example.crud.DatabaseConnection;
import com.example.crud.TaskStatus;
import com.example.crud.Tasks;
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
import java.util.stream.Collectors;

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
            new Tasks("Task 1", false, PENDING),
            new Tasks("Random task",  false, COMPLETED),
            new Tasks("Task 2",  false, IN_PROGRESS),
            new Tasks("Fourth task",  false, COMPLETED),
            new Tasks("girggle",  false, IN_PROGRESS),
            new Tasks("tasksssss",  false, PENDING)
    );

    @FXML
    private void initialize() {
        tableView.setEditable(true); // Enable editing on the Tableview

        // Creating columns for the tables
        taskTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        taskSelectedColumn.setCellValueFactory(new PropertyValueFactory<>("selected"));
        taskStatusColumn.setCellValueFactory(new PropertyValueFactory<>("taskStatus"));
        taskSelectedColumn.setCellValueFactory(cellData -> cellData.getValue().selectedProperty());
        taskSelectedColumn.setCellFactory(CheckBoxTableCell.forTableColumn(taskSelectedColumn));
        taskSelectedColumn.setEditable(true);

        // Using TextFieldTableCell for the task title column
        taskTitleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        taskStatusColumn.setCellFactory(ComboBoxTableCell.forTableColumn(
                FXCollections.observableArrayList( values()) // Using enum constants
        ));

        // Handles edit commit for tasks that has a checkbox checked
        taskSelectedColumn.setOnEditCommit(event -> {
            Tasks task = event.getRowValue();
            task.setSelected(event.getNewValue());
        });

        //TODO : write code to listen for checkbox and stores it in psql table

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

        for (Tasks task: tasksList) {
            DatabaseConnection.insertTask(task.getTitle(), task.selectedProperty().get(), task.getTaskStatus());

        }

    }

    @FXML
    protected void addTaskButtonClick(){
        Tasks task = new Tasks(textField.getText(), false, PENDING); //
        DatabaseConnection.insertTask(task.getTitle(), task.selectedProperty().get(), task.getTaskStatus());
        System.out.println(tasksList.add(task) ? "Task as been added" : "No task has been added");
        textField.clear();
        tableView.setItems(tasksList);
    }
    @FXML
    protected void deleteTaskButtonClick(){
        System.out.println("Task delete button pressed");

        ObservableList<Tasks> selectedTasks = FXCollections.observableArrayList(
                tableView.getItems()
                        .stream()
                        .filter(task -> task.getSelected()) // Filters tasks that has been marked checked
                        .collect(Collectors.toList())
        );

        for (Tasks task : selectedTasks) {
            DatabaseConnection.deleteTask(task.getTitle());
        }

        tasksList.removeAll(selectedTasks);

        tableView.setItems(tasksList);
    }
    @FXML
    protected void markTaskButtonClick(){

    }



}