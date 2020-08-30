
package com.cmuteam.app.Models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ApiResponse {

    private Integer resultsFound;
    private Integer resultsStart;
    private Integer resultsShown;
    private List<Restaurant> restaurants;

    public Integer getResultsFound() {
        return resultsFound;
    }

    public void setResultsFound(Integer resultsFound) {
        this.resultsFound = resultsFound;
    }

    public Integer getResultsStart() {
        return resultsStart;
    }

    public void setResultsStart(Integer resultsStart) {
        this.resultsStart = resultsStart;
    }

    public Integer getResultsShown() {
        return resultsShown;
    }

    public void setResultsShown(Integer resultsShown) {
        this.resultsShown = resultsShown;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }



}
