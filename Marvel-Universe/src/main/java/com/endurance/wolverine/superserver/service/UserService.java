package com.endurance.wolverine.superserver.service;

import com.endurance.wolverine.superserver.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.codehaus.groovy.runtime.powerassert.SourceText;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kapish on 17/6/17.
 */
@Service
public class UserService {

    @Autowired
    HttpSession httpSession;

    @Autowired
    Environment environment;

    public User findByUserName(String userName) throws UnirestException, IOException {
        String loggedInUserGroup = (String) httpSession.getAttribute("loggedInUserGroup");
        String url = "";
        if(loggedInUserGroup.equals("0")){
            url = environment.getProperty("ipaddr.avengers")+"/avengers/user/"+userName;
        }
        else{
            url = environment.getProperty("ipaddr.xmen")+"/xmen/user/"+userName;

        }
        HttpResponse<JsonNode> httpResponse = RequestHandler.sendGetRequest(url);
        ObjectMapper mapper = new ObjectMapper();
        if(httpResponse.getStatus() == HttpURLConnection.HTTP_OK){

            User fetchedUser = mapper.readValue(httpResponse.getBody().toString(),User.class);
            //System.out.println(user.getUserPassword()+" "+fetchedUser.getUserPassword());
            return fetchedUser;
        }
        return null;

    }

    public ArrayList<User> findByUserGroup(String loggedInUserGroup) throws UnirestException {
        String url = "";
        if(loggedInUserGroup.equals("0")){
            url = environment.getProperty("ipaddr.avengers")+"avengers/user/";
        }
        else{
            url = environment.getProperty("ipaddr.xmen")+"xmen/user/";

        }
        System.out.println(url);
        HttpResponse<JsonNode> httpResponse = RequestHandler.sendGetRequest(url);
        Gson gson = new Gson();
        Type listType = new TypeToken<List<User>>(){}.getType();
        ArrayList<User> users = (ArrayList<User>) gson.fromJson(httpResponse.getBody().toString(), listType);
        return users;
    }
}
