package com.endurance.wolverine.superserver.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by kapish on 16/6/17.
 */

public class User {

    private String userName;
    private String userEmail;
    private String userFirstName;
    private String userLastName;
    private String userSuperPower;
    private String userPassword;
    private String userGroup;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserSuperPower() {
        return userSuperPower;
    }

    public void setUserSuperPower(String userSuperPower) {
        this.userSuperPower = userSuperPower;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

}
