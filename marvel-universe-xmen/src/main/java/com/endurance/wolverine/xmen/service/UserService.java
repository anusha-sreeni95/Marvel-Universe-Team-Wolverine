package com.endurance.wolverine.xmen.service;

import com.endurance.wolverine.xmen.model.User;

import java.util.ArrayList;

/**
 * Created by anusha on 17/6/17.
 */
public interface UserService {
    public User findByUserName(String userName);
    public ArrayList<User> findAllUsersByUserGroup();
    public Boolean saveUser(User user);
    public String getBio(String userName);
}
