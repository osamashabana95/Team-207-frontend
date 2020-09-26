package com.alice.afroapp;

public class Question {

    private String questionId;
    private String title;
    String solution;

    public Question(String questionId, String title, String solution) {
        this.questionId = questionId;
        this.title = title;
        this.solution = solution;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
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
}
