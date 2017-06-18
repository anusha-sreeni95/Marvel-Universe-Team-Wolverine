package com.endurance.wolverine.xmen.service;

import com.endurance.wolverine.xmen.model.Task;

import java.util.ArrayList;

/**
 * Created by anusha on 17/6/17.
 */
public interface TaskService {
    public Task findByTaskName(String taskName);
    public ArrayList<Task> findPersonalTasks(String taskCreator);
    public ArrayList<Task> findSharedTasks();
    //public ArrayList<Task> findByTaskSquad();
    public Boolean saveTask(Task task);
    public Task updateTask(String taskId);
}