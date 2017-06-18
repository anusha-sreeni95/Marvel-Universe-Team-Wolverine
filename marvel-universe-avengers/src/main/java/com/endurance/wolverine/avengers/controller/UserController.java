package com.endurance.wolverine.avengers.controller;

import com.endurance.wolverine.avengers.model.User;
import com.endurance.wolverine.avengers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Created by anusha on 17/6/17.
 */
@RestController
@RequestMapping("/avengers")
public class UserController {
    @Autowired
    UserService userService;


    @RequestMapping(value="/user/{userName}",method= RequestMethod.GET)
    public User getUser(@PathVariable("userName") String userName, HttpServletResponse httpServletResponse){
        User user = userService.findByUserName(userName);
        if(user != null){
            httpServletResponse.setStatus(200);
        }
        else {
            //resource not found
            httpServletResponse.setStatus(404);
        }
        return user;
    }

    @RequestMapping(value="/user",method=RequestMethod.GET)
    public ArrayList<User> findAllUsers(HttpServletResponse httpServletResponse){
        ArrayList<User> users = userService.findAllUsersByUserGroup();
        if(users != null){
            httpServletResponse.setStatus(200);
        }
        else {
            //resource not found
            httpServletResponse.setStatus(404);
        }
        return users;
    }

    @RequestMapping(value="/user",method=RequestMethod.POST)
    public User addUser(@RequestBody User user){
        userService.saveUser(user);
        return user;
    }

    @RequestMapping(value="/user/bio/{userName}", method=RequestMethod.GET)
    public String getBio(@PathVariable("userName") String userName,HttpServletResponse httpServletResponse){
        return userService.getBio(userName);
    }
}
