package com.endurance.wolverine.xmen.repository;

import com.endurance.wolverine.xmen.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

/**
 * Created by anusha on 17/6/17.
 */
public interface UserRepository extends CrudRepository<User, String> {
    User findByUserName(String userName);
    ArrayList<User> findAll();

}