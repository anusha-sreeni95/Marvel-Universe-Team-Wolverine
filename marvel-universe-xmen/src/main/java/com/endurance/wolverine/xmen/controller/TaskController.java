package com.endurance.wolverine.xmen.controller;

import com.endurance.wolverine.xmen.model.Task;
import com.endurance.wolverine.xmen.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Created by anusha on 17/6/17.
 */
@RestController
@RequestMapping("/xmen")
public class TaskController {
    @Autowired
    TaskService taskService;

    @RequestMapping(value="/task/{taskName}",method= RequestMethod.GET)
    public Task getTask(@PathVariable("taskName") String taskName,HttpServletResponse httpServletResponse){
        Task task = taskService.findByTaskName(taskName);
        if(task != null){
            httpServletResponse.setStatus(200);
        }
        else {
            //resource not found
            httpServletResponse.setStatus(404);
        }
        return task;
    }

    @RequestMapping(value="/task/personal/{taskCreator}",method=RequestMethod.GET)
    public ArrayList<Task> findPersonalTasks(@PathVariable("taskCreator") String taskCreator, HttpServletResponse httpServletResponse){
        ArrayList<Task> tasks = taskService.findPersonalTasks(taskCreator);
        if(tasks != null){
            httpServletResponse.setStatus(200);
        }
        else {
            //resource not found
            httpServletResponse.setStatus(404);
        }
        return tasks;
    }

    @RequestMapping(value="/task/shared",method=RequestMethod.GET)
    public ArrayList<Task> findSharedTasks( HttpServletResponse httpServletResponse){
        ArrayList<Task> tasks = taskService.findSharedTasks();
        if(tasks != null){
            httpServletResponse.setStatus(200);
        }
        else {
            //resource not found
            httpServletResponse.setStatus(404);
        }
        return tasks;
    }

//    @RequestMapping(value="/task",method=RequestMethod.GET)
//    public ArrayList<Task> findAllTasks(HttpServletResponse httpServletResponse){
//        ArrayList<Task> tasks = taskService.findByTaskSquad();
//        if(tasks != null){
//            httpServletResponse.setStatus(200);
//        }
//        else {
//            //resource not found
//            httpServletResponse.setStatus(404);
//        }
//        return tasks;
//    }


    @RequestMapping(value="/task",method= RequestMethod.POST)
    public Task addTask(@RequestBody Task task){
        taskService.saveTask(task);
        return task;
    }

    @RequestMapping(value="/task/update/{taskId}",method=RequestMethod.PUT)
    public Task updateTask(@PathVariable("taskId") String taskId){
        Task task=taskService.updateTask(taskId);
        return task;
    }
}

