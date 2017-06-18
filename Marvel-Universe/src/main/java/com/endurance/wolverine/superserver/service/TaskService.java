package com.endurance.wolverine.superserver.service;

import com.endurance.wolverine.superserver.model.Task;
import com.endurance.wolverine.superserver.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kapish on 17/6/17.
 */
@Service
public class TaskService {

    @Autowired
    HttpSession httpSession;


    @Autowired
    Environment environment;

    @Autowired
    SessionService sessionService;

    public ArrayList<Task> findPersonalToDo() throws UnirestException {
        String url = "";
        if(sessionService.getLoggedinUserGroup().equals("0")){
            url = environment.getProperty("ipaddr.avengers")+"avengers/task/personal/"+sessionService.getUserName();
        }
        else{
            url = environment.getProperty("ipaddr.xmen")+"xmen/task/personal/"+sessionService.getUserName();
        }
        HttpResponse<JsonNode> httpResponse = RequestHandler.sendGetRequest(url);
        System.out.println(httpResponse.getBody().toString());
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Task>>(){}.getType();
        ArrayList<Task> tasks = (ArrayList<Task>) gson.fromJson(httpResponse.getBody().toString(), listType);
        return tasks;
    }

    public boolean savePersonalTask(String taskName) throws JsonProcessingException, UnirestException {

        Task task = new Task();

        task.setTaskName(taskName);
        task.setTaskCreator(sessionService.getUserName());
        task.setTaskStatus("i");
        String url = "";
        if(sessionService.getLoggedinUserGroup().equals("0")){
            url = environment.getProperty("ipaddr.avengers")+"/avengers/task";
            task.setTaskSquad("3");

        }
        else{
            url = environment.getProperty("ipaddr.xmen")+"/xmen/task";
            task.setTaskSquad("4");
        }
        HttpResponse<JsonNode> httpResponse = RequestHandler.sendPostRequest(url,task);
        if(httpResponse.getStatus() == HttpURLConnection.HTTP_OK){
            return true;
        }
        else{
            return false;
        }
    }

    public ArrayList<Task> findSquadToDo() throws UnirestException {
        String url = "";
        if(sessionService.getLoggedinUserGroup().equals("0")){
            url = environment.getProperty("ipaddr.avengers")+"avengers/task/shared/";
        }
        else{
            url = environment.getProperty("ipaddr.xmen")+"xmen/task/shared/";
        }
        HttpResponse<JsonNode> httpResponse = RequestHandler.sendGetRequest(url);
        System.out.println(httpResponse.getBody().toString());
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Task>>(){}.getType();
        ArrayList<Task> tasks = (ArrayList<Task>) gson.fromJson(httpResponse.getBody().toString(), listType);
        return tasks;

    }

    public boolean saveSharedTask(String taskName) throws JsonProcessingException, UnirestException {
        Task task = new Task();

        task.setTaskName(taskName);
        task.setTaskCreator(sessionService.getUserName());
        task.setTaskStatus("i");
        String url = "";
        if(sessionService.getLoggedinUserGroup().equals("0")){
            url = environment.getProperty("ipaddr.avengers")+"/avengers/task";
            task.setTaskSquad("0");

        }
        else{
            url = environment.getProperty("ipaddr.xmen")+"/xmen/task";
            task.setTaskSquad("1");
        }
        HttpResponse<JsonNode> httpResponse = RequestHandler.sendPostRequest(url,task);
        if(httpResponse.getStatus() == HttpURLConnection.HTTP_OK){
            return true;
        }
        else{
            return false;
        }

    }
}

