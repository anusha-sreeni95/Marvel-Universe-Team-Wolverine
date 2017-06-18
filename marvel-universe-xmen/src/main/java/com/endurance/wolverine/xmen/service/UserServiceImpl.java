package com.endurance.wolverine.xmen.service;

import com.endurance.wolverine.xmen.model.User;
import com.endurance.wolverine.xmen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by anusha on 17/6/17.
 */
@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public User findByUserName(String userName) {
        User user=userRepository.findByUserName(userName);
        if(user!=null && (user.getUserGroup().equals("1") || user.getUserGroup().equals("2"))){
            return user;
        }
        else{
            return null;
        }
    }

    @Override
    public ArrayList<User> findAllUsersByUserGroup() {
        ArrayList<User> users=userRepository.findAll();
        ArrayList<User> xmen=new ArrayList<User>();
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if(user.getUserGroup().equals("1") || user.getUserGroup().equals("2")){
                xmen.add(user);
            }
        }
        return xmen;
    }

    @Override
    public Boolean saveUser(User user) {
        if(userRepository.findByUserName(user.getUserName())==null){
            userRepository.save(user);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String getBio(String userName){
        return userRepository.findByUserName(userName).getBio();
    }

}