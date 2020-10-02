package com.alice.afroapp;

public class Question {

    private String id;
    private String title;
    private String solution;
    private String username;

    public Question() { }

    public Question(String id, String title, String solution,String username) {
        this.id = id;
        this.title = title;
        this.solution = solution;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
