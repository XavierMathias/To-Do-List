package com.example.crud;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Tasks {

    private static int idCounter = 0;
    private final StringProperty title = new SimpleStringProperty();
    private BooleanProperty selected = new SimpleBooleanProperty();
    private ObjectProperty<TaskStatus> taskStatus = new SimpleObjectProperty<>();

    public Tasks(String title, TaskStatus status){
        this.title.set(title);
        this.taskStatus.set(status);

    }

    @Override
    public String toString() {
        return "Tasks{" +
                "title=" + title +
                ", selected=" + selected +
                ", taskStatus=" + taskStatus.getName() +
                '}';
    }

    public boolean getSelected() {
        return selected.get();
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }

    public BooleanProperty selectedProperty() {
        return selected;
    }

    public String getTitle() {
        return this.title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public TaskStatus getTaskStatus() {
        return this.taskStatus.get();
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus.set(taskStatus);
    }
}
