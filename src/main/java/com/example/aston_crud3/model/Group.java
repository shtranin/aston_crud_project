package com.example.aston_crud3.model;

public class Group {
    int id;
    String name;
    int userCount;

    public Group(String name, int user_count) {
        this.name = name;
        this.userCount = user_count;
    }

    public Group(int id, String name, int user_count) {
        this.id = id;
        this.name = name;
        this.userCount = user_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }
}
