package com.example.mnrr.whattodolist;

/**
 * Created by megha on 01-11-2016.
 */
public class Task {
    private String title, details;

    public Task() {
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Task(String title, String details) {
        this.title = title;
        this.details = details;
    }

    public Task(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }


}
