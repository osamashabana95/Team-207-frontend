package com.alice.afroapp;

public class NewQuestion {

    private String id;
    private String title;
    private String userName;

    public NewQuestion(String id,String title, String userName) {

        this.id = id;
        this.title = title;
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

}
