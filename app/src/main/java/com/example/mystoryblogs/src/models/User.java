package com.example.mystoryblogs.src.models;

public class User {
    private String 

    //Setters and getters

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "token:"+token;
    }
}