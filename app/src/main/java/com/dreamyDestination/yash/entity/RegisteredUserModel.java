package com.dreamyDestination.yash.entity;

import java.util.ArrayList;

public class RegisteredUserModel extends ArrayList<RegisteredUserModel>
{
    private String user_full_name;
    private String user_id;
    private String user_email;


    public RegisteredUserModel(String user_email, String user_full_name, String user_id) {
        this.user_email = user_email;
        this.user_full_name = user_full_name;
        this.user_id = user_id;

    }

    public RegisteredUserModel() {

    }

    public String getUser_full_name() {
        return user_full_name;
    }

    public void setUser_full_name(String user_full_name) {
        this.user_full_name = user_full_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String owner_name) {
        this.user_id = user_id;
    }

}


