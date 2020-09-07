package com.click2eat.app.models;

import java.util.List;

public class Restaurant_ {
    private String id;
    private String name;
    private Location location;
    private String cuisines;
    private String timings;
    private Integer average_cost_for_two;
    private String price_range;
    private String currency;
    private UserRating user_rating;
    private String phone_numbers;
    private List<String> establishment;
    private double distance;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


    public String getCuisines() {
        return cuisines;
    }

    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public Integer getAverageCostForTwo() {
        return average_cost_for_two;
    }

    public void setAverageCostForTwo(Integer averageCostForTwo) {
        this.average_cost_for_two = averageCostForTwo;
    }

    public String getPriceRange() {
        return price_range;
    }

    public void setPriceRange(String priceRange) {
        this.price_range = priceRange;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }


    public UserRating getUserRating() {
        return user_rating;
    }

    public void setUserRating(UserRating userRating) {
        this.user_rating = userRating;
    }


    public String getPhoneNumbers() {
        return phone_numbers;
    }

    public void setPhoneNumbers(String phoneNumbers) {
        this.phone_numbers = phoneNumbers;
    }


    public List<String> getEstablishment() {
        return establishment;
    }

    public void setEstablishment(List<String> establishment) {
        this.establishment = establishment;
    }


    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
