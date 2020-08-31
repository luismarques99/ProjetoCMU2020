package com.cmuteam.app.Models;

public class TestRestaurant {
    private int id;
    private String name;

    public TestRestaurant(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
