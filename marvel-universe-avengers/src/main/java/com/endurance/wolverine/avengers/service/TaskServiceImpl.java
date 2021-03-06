package com.endurance.wolverine.avengers.service;

import com.endurance.wolverine.avengers.model.Task;
import com.endurance.wolverine.avengers.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by anusha on 17/6/17.
 */
@Service("taskService")
public class TaskServiceImpl implements TaskService{
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task findByTaskName(String taskName) {
        Task task=taskRepository.findByTaskName(taskName);
        if(task!=null && (task.getTaskSquad().equals("0") || task.getTaskSquad().equals("2"))){
            return task;
        }
        else{
            return null;
        }
    }

    @Override
    public ArrayList<Task> findPersonalTasks(String taskCreator){
        ArrayList<Task> tasks=taskRepository.findByTaskCreator(taskCreator);
        ArrayList<Task> myTasks=new ArrayList<Task>();
        Iterator<Task> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            Task task = iterator.next();
            if(task.getTaskSquad().equals("3")){
                myTasks.add(task);
            }
        }
        return myTasks;
    }

    @Override
    public ArrayList<Task> findSharedTasks(){
        ArrayList<Task> tasks=taskRepository.findAll();
        ArrayList<Task> avengersTask=new ArrayList<Task>();
        Iterator<Task> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            Task task = iterator.next();
            if(task.getTaskSquad().equals("0")){
                avengersTask.add(task);
            }
        }
        return avengersTask;
    }


    @Override
    public Boolean saveTask(Task task) {
        if(taskRepository.findByTaskId(task.getTaskId())==null){
            taskRepository.save(task);
            return true;
        }
        else {
            return false;
        }
    }

    public Task updateTask(String taskId){
        Task task=taskRepository.findByTaskId(taskId);
        if(task.getTaskStatus().equals("i")){
            task.setTaskStatus("c");
            taskRepository.save(task);
        }
        return task;
    }
}