package com.endurance.wolverine.avengers.model;

import javax.persistence.*;

/**
 * Created by anusha on 17/6/17.
 */
@Entity
@Table(name="task")
public class Task {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "task_id")
    private String taskId;
    @Column(name = "task_name")
    private String taskName;
    @Column(name = "task_status")
    private String taskStatus;
    @Column(name = "task_squad")
    private String taskSquad;
    @Column(name = "task_creator")
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

