package com.example.crud;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Tasks {

    private int id;
    private static int idCounter = 0;
    private final StringProperty title = new SimpleStringProperty();
    private BooleanProperty selected = new SimpleBooleanProperty();
    private ObjectProperty<TaskStatus> taskStatus = new SimpleObjectProperty<>();

    public Tasks(int id, String title, TaskStatus status){
        this.id = id;
        this.title.set(title);
        this.taskStatus.set(status);
        this.selected.set(false);

    }

    @Override
    public String toString() {
        return "Tasks{" +
                "id=" + id +
                ", title=" + title +
                ", selected=" + selected +
                ", taskStatus=" + taskStatus +
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getTaskStatus() {
        return this.taskStatus.get().getDescription();
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus.set(taskStatus);
    }
}
