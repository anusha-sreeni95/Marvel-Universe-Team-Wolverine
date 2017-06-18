package com.endurance.wolverine.superserver.controller;

import com.endurance.wolverine.superserver.model.User;
import com.endurance.wolverine.superserver.service.LoginService;
import com.endurance.wolverine.superserver.service.SessionService;
import com.endurance.wolverine.superserver.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by kapish on 17/6/17.
 */
@Controller
public class LoginController {

    @Autowired
    SessionService sessionService;

    @Autowired
    LoginService loginService;

    @Autowired
    UserService userService;

    @Autowired
    org.springframework.core.env.Environment environment;
    @RequestMapping(value="/",method= RequestMethod.GET)
    public ModelAndView getHomePage() throws UnirestException {
        ModelAndView modelAndView = new ModelAndView();
        if(sessionService.isSessionCreated()){
            String displayName = sessionService.getHeadingForUserSite();
            ArrayList<User> users = userService.findByUserGroup(sessionService.getLoggedinUserGroup());
            modelAndView.addObject("users",users);
            modelAndView.addObject("name",displayName);
            modelAndView.setViewName("dashboard");
        }
        else{
            //sessionService.createSession("avengers");
            modelAndView.addObject("msg","");
            modelAndView.setViewName("login");
        }

        return modelAndView;
    }

    @RequestMapping(value="/avengers", method=RequestMethod.GET)
    public String showAvengerSite(){
        if(sessionService.getUserGroup().equals("2")){
            if(sessionService.getLoggedinUserGroup().equals("0")){
                sessionService.changeLoggedInUserGroup();
            }
            return "redirect:/";

        }
        else{
            return "errorfile";
        }
    }


    @RequestMapping(value="/xmen" ,method=RequestMethod.GET)
    public String showXMenSite(){
        if(sessionService.getUserGroup().equals("2")){
            if(sessionService.getLoggedinUserGroup().equals("0")){
                sessionService.changeLoggedInUserGroup();
            }
            return "redirect:/";

        }
        else{
            return "error";
        }

    }

    @RequestMapping(value="/login",method=RequestMethod.POST)
    public String doSignIn(@ModelAttribute User user, Model model){
        try {
            if(loginService.isAuthenticated(user)){
                //return ModelView
                sessionService.createSession(user);
                return "redirect:/";

            }
            else{
                //return error
                model.addAttribute("msg","Please check your login details");
                return "login";
            }
        } catch (UnirestException e) {
            e.printStackTrace();
            model.addAttribute("msg","Not able to sigin. Please try again later");
            return "login";
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("msg","Not able to sigin. Please try again later");
            return "login";
        }

    }

    @RequestMapping(value="/registeration",method=RequestMethod.POST)
    public String doRegisteration(@ModelAttribute User user, Model model)  {
        boolean isRegistered = false;
        try {
            isRegistered = loginService.registerUser(user);
        } catch (JsonProcessingException e) {

        } catch (UnirestException e) {
            e.printStackTrace();
            model.addAttribute("msg","Not Able to register successfully. Please try again later");
            return "register";
        }
        if(isRegistered){
            model.addAttribute("msg","You are registered successfully");
            return "login";
        }
        else{
            model.addAttribute("msg","Not Able to register successfully. Please try again later");
            return "register";
        }
    }


    @RequestMapping(value="/registeration",method=RequestMethod.GET)
    public ModelAndView getRegisterPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @RequestMapping(value="/signout", method=RequestMethod.GET)
    public String signOutFromDashBoard(){
        ModelAndView modelAndView = new ModelAndView();
        sessionService.deleteSession();
        String redirectUrl = "/";
        return "redirect:" + redirectUrl;
    }

}
