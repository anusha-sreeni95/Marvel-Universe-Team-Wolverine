package com.endurance.wolverine.xmen.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by anusha on 17/6/17.
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_email")
    private String userEmail;
    @Column(name = "user_firstname")
    private String userFirstName;
    @Column(name = "user_lastname")
    private String userLastName;
    @Column(name = "user_superpower")
    private String userSuperPower;
    @Column(name = "user_password")
    private String userPassword;
    @Column(name="user_group")
    private String userGroup;
    @Column(name="bio",length = 10000)
    private String bio;

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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}