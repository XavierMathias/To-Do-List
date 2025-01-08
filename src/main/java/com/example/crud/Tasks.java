package com.example.crud;

import java.time.LocalDate;

public class Tasks {

    private int id;
    private static int idCounter = 0;
    private String title;
    private boolean selected = false;
    private TaskStatus taskStatus;

    public Tasks(int id, String title, TaskStatus status){
        this.id = id;
        this.title = title;
        taskStatus = status;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTaskStatus() {
        return taskStatus.getDescription();
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }
}
