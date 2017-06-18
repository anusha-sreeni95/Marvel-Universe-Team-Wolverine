package com.endurance.wolverine.superserver.service;

import com.endurance.wolverine.superserver.model.Task;
import com.endurance.wolverine.superserver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by kapish on 17/6/17.
 */
@Service
public class SessionService {
    @Autowired
    private HttpSession httpSession;

    public Boolean isSessionCreated(){
        if(httpSession.getAttribute("userName") != null){
           return true;
        }
        else{
            return false;
        }

    }

    public void createSession(User user){

        httpSession.setAttribute("userName",user.getUserName());
        httpSession.setAttribute("loggedInUserGroup",user.getUserGroup());


    }

    public String getHeadingForUserSite(){
        String displayName = "";
        if(getLoggedinUserGroup().equals("0")){
            displayName = "Hi Avengers " + getUserName();
        }
        else{
            displayName = "Hi X Men " + getUserName();
        }
        return displayName;
    }
    public void deleteSession(){
        httpSession.invalidate();
    }


    public String getLoggedinUserGroup() {
        return (String) httpSession.getAttribute("loggedInUserGroup");
    }

    public String getUserName() {
        return (String) httpSession.getAttribute("userName");
    }

    public void setSessionUserGroup(String userGroup){
        httpSession.setAttribute("userGroup",userGroup);
    }

    public void changeLoggedInUserGroup() {
        if(httpSession.getAttribute("loggedInUserGroup").equals("0")){
            httpSession.setAttribute("loggedInUserGroup","1");
        }
        else{
            httpSession.setAttribute("loggedInUserGroup","0");
        }
    }

    public String getUserGroup() {
        return (String) httpSession.getAttribute("userGroup");
    }


}
