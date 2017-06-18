package com.endurance.wolverine.superserver.controller;

import com.endurance.wolverine.superserver.model.Task;
import com.endurance.wolverine.superserver.model.User;
import com.endurance.wolverine.superserver.service.SessionService;
import com.endurance.wolverine.superserver.service.TaskService;
import com.endurance.wolverine.superserver.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by kapish on 17/6/17.
 */
@Controller
public class DashBoardController {
    @Autowired
    SessionService sessionService;

    
    @Autowired
    UserService userService;

    @Autowired
    TaskService taskService;
    
    @RequestMapping(value="/switchsite",method= RequestMethod.GET)
    public String switchHomePage() throws IOException, UnirestException {
        if(sessionService.isSessionCreated()){
            User user = userService.findByUserName(sessionService.getUserName());
            if(user.getUserGroup().equals("2")){
                sessionService.changeLoggedInUserGroup();
            }
        }

        return "redirect:/";
    }

    @RequestMapping(value="/personaltask",method=RequestMethod.GET)
    public ModelAndView displayPersonalTasks() throws UnirestException {
        ModelAndView modelAndView = new ModelAndView();
        if(sessionService.isSessionCreated()){

            String displayName = sessionService.getHeadingForUserSite();
            ArrayList<Task> tasks = taskService.findPersonalToDo();
            modelAndView.addObject("name",displayName);
            modelAndView.addObject("tasks",tasks);
            modelAndView.setViewName("personaltodo");
        }
        else{
            modelAndView.setViewName("redirect:/");

        }
        return modelAndView;

    }

    @RequestMapping(value="/squadtask",method=RequestMethod.GET)
    public ModelAndView displaySquadTasks() throws UnirestException {
        ModelAndView modelAndView = new ModelAndView();
        if(sessionService.isSessionCreated()){

            String displayName = sessionService.getHeadingForUserSite();
            ArrayList<Task> tasks = taskService.findSquadToDo();
            modelAndView.addObject("name",displayName);
            modelAndView.addObject("tasks",tasks);
            modelAndView.setViewName("squadtodo");
        }
        else{
            modelAndView.setViewName("redirect:/");

        }
        return modelAndView;

    }

    @RequestMapping(value = "/addpersonaltask",method=RequestMethod.POST)
    public ModelAndView addNewPersonalTask(@RequestParam("taskName") String taskName) throws JsonProcessingException, UnirestException {
        ModelAndView modelAndView = new ModelAndView();
        if(sessionService.isSessionCreated()){
            boolean isTaskSaved = taskService.savePersonalTask(taskName);
            modelAndView.setViewName("redirect:/personaltask");
        }
        else{
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/addsharedtask",method=RequestMethod.POST)
    public ModelAndView addNewSharedTask(@RequestParam("taskName") String taskName) throws JsonProcessingException, UnirestException {
        ModelAndView modelAndView = new ModelAndView();
        if(sessionService.isSessionCreated()){
            boolean isTaskSaved = taskService.saveSharedTask(taskName);
            modelAndView.setViewName("redirect:/squadtask");
        }
        else{
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;
    }
}
