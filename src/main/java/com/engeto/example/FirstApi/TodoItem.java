package com.engeto.example.FirstApi;

public class TodoItem {
    private Integer id;
    private String item;
    private Boolean done;

    public TodoItem() {
    }

    public TodoItem(String item, Boolean done) {
        this.item = item;
        this.done = done;
    }

    public TodoItem(Integer id, String item, Boolean done) {
        this.id = id;
        this.item = item;
        this.done = done;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
