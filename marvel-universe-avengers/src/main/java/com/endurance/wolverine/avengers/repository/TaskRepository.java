package com.endurance.wolverine.avengers.repository;

import com.endurance.wolverine.avengers.model.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

/**
 * Created by anusha on 17/6/17.
 */
public interface TaskRepository extends CrudRepository<Task,String> {
    Task findByTaskName(String taskName);
    Task findByTaskId(String taskId);
    ArrayList<Task> findByTaskCreator(String taskName);
    ArrayList<Task> findAll();
}