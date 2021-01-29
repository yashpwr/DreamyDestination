package com.dreamyDestination.yash.entity;

/**
 * Created by Rupesh on 02-Mar-18.
 */

public class ELUserInfo {

    String full_name;
    String user_email_id;
    String user_unique_id;
    String joined_date;
    String user_password;

    public String getFullName() {
        return full_name;
    }

    public void setFullName(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return user_email_id;
    }

    public void setEmail(String user_email_id) {
        this.user_email_id = user_email_id;
    }

    public String getUser_unique_id() {
        return user_unique_id;
    }

    public void setUser_unique_id(String user_unique_id) {
        this.user_unique_id = user_unique_id;
    }

    public String getJoined_date() {
        return joined_date;
    }

    public void setJoined_date(String joined_date) {
        this.joined_date = joined_date;
    }


    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

}
