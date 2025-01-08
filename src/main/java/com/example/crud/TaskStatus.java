package com.example.crud;

public enum TaskStatus {
    PENDING("Not Started"), // The task has not started
    IN_PROGRESS("In Progress"), // The task is currently being worked on
    COMPLETED("Done");// The task is done

    private final String description;
    TaskStatus(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
