package com.alice.afroapp;

import java.io.Serializable;

public class Mentor implements Serializable {
    private  String id;
    private  String fullname;
    private String proficiency;
    private String location;
    private String email;
    private String imageUrl;
    private String imageName;
    private String key;



    private String username;


    public void Mentor() {
    }

    public  Mentor(){}

    public Mentor(String id, String fullname, String proficiency, String location,
                  String email, String imageUrl, String imageName, String key,String username) {
        this.id = id;
        this.fullname = fullname;
        this.proficiency = proficiency;
        this.location = location;
        this.email = email;
        this.imageUrl = imageUrl;
        this.imageName = imageName;
        this.key = key;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getProficiency() {
        return proficiency;
    }

    public void setProficiency(String proficiency) {
        this.proficiency = proficiency;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
