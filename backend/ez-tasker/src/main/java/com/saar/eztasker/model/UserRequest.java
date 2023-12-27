package com.saar.eztasker.model;

public class UserRequest {
    private String username;


    public UserRequest(String username) {
        this.username = username;
    }
    public UserRequest() {
    }

    public String GetUsername() {
        return username;
    }

    public void SetUsername(String username) {
        this.username = username;
    }


    public String toUser() {
        return username;
    }
}
