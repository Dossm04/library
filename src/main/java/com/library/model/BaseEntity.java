package com.library.model;

public abstract class BaseEntity {
    protected int id;
    protected String title;


    public abstract String getEntityType();
    public abstract void validate();

    public String info() {
        return getEntityType() + " #" + id + " - " + title;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

}