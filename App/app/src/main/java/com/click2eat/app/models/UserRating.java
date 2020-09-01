package com.click2eat.app.models;

public class UserRating {

    private String aggregate_rating;
    private String rating_text;
    private String rating_color;
    private RatingObj ratingObj;
    private String votes;


    public String getAggregateRating() {
        return aggregate_rating;
    }

    public void setAggregateRating(String aggregateRating) {
        this.aggregate_rating = aggregateRating;
    }

    public String getRatingText() {
        return rating_text;
    }

    public void setRatingText(String ratingText) {
        this.rating_text = ratingText;
    }

    public String getRatingColor() {
        return rating_color;
    }

    public void setRatingColor(String ratingColor) {
        this.rating_color = ratingColor;
    }

    public RatingObj getRatingObj() {
        return ratingObj;
    }

    public void setRatingObj(RatingObj ratingObj) {
        this.ratingObj = ratingObj;
    }

    public String getVotes() {
        return votes;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }


}
