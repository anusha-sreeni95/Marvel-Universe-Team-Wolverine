package com.endurance.wolverine.superserver.service;

import com.endurance.wolverine.superserver.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Created by kapish on 17/6/17.
 */
@Service
public class LoginService {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private Environment environment;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private static String ipAvengersText = "ipaddr.avengers";
    private static String ipXMenText = "ipaddr.xmen";

    public boolean isAuthenticated(User user) throws UnirestException, IOException {
        //call end user endpoint
        String url = "";
        ObjectMapper mapper = new ObjectMapper();

        //user.setUserPassword(bCryptPasswordEncoder.encode(user.getUserPassword()));
        if(user.getUserGroup().equals("0")){
            url = environment.getProperty(ipAvengersText)+"/avengers/user/"+user.getUserName();
        }
        else{
            url = environment.getProperty(ipXMenText)+"/xmen/user/"+user.getUserName();

        }
        System.out.println(url);
        HttpResponse<JsonNode> httpResponse = RequestHandler.sendGetRequest(url);
        //System.out.println(httpResponse.getStatus()+httpResponse.getStatusText());
        if(httpResponse.getStatus() == HttpURLConnection.HTTP_OK){

            User fetchedUser = mapper.readValue(httpResponse.getBody().toString(),User.class);
            System.out.println(bCryptPasswordEncoder.matches(user.getUserPassword(),fetchedUser.getUserPassword()));

            if(bCryptPasswordEncoder.matches(user.getUserPassword(),fetchedUser.getUserPassword()) && (fetchedUser.getUserGroup().equals(user.getUserGroup()) || fetchedUser.getUserGroup().equals("2"))){
                httpSession.setAttribute("userGroup",fetchedUser.getUserGroup());
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }

    public boolean registerUser(User user) throws JsonProcessingException, UnirestException {
        //call register user end point
        user.setUserPassword(bCryptPasswordEncoder.encode(user.getUserPassword()));
        String url = environment.getProperty(ipAvengersText)+"/avengers/user";
        HttpResponse<JsonNode> httpResponse = RequestHandler.sendPostRequest(url,user);
        System.out.println(httpResponse.getStatus()+httpResponse.getStatusText());
        if(httpResponse.getStatus() == HttpURLConnection.HTTP_OK){
            return true;
        }
        else{
            return false;
        }
    }
}
