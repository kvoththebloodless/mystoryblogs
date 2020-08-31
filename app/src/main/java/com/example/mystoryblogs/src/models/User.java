package com.example.mystoryblogs.src.models;

public class User {
    String authorName;
    String userName;
    String bio;
    String[] blogIds;
    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String[] getBlogIds() {
        return blogIds;
    }

    public void setBlogIds(String[] blogIds) {
        this.blogIds = blogIds;
    }




}
