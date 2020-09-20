package com.alice.afroapp;

public class Mentor {
    private  String id;
    private  String fullname;
    private String proficiency;
    private String location;
    private String email;
    private String imageUrl;
    private String imageName;
    public  Mentor(){}

    public Mentor(String id, String fullname, String proficiency, String location,
                  String email, String imageUrl, String imageName) {
        this.id = id;
        this.fullname = fullname;
        this.proficiency = proficiency;
        this.location = location;
        this.email = email;
        this.imageUrl = imageUrl;
        this.imageName = imageName;
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
}
