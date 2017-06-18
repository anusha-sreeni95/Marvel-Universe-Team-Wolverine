package com.endurance.wolverine.superserver.model;

import javax.persistence.*;

/**
 * Created by anusha on 17/6/17.
 */
public class Task {

    private String taskId;
    private String taskName;
    private String taskStatus;
    private String taskSquad;
    private String taskCreator;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskSquad() {
        return taskSquad;
    }

    public void setTaskSquad(String taskSquad) {
        this.taskSquad = taskSquad;
    }

    public String getTaskCreator() {
        return taskCreator;
    }

    public void setTaskCreator(String taskCreator) {
        this.taskCreator = taskCreator;
    }

}

