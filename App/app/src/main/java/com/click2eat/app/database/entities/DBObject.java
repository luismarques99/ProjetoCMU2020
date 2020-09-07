package com.click2eat.app.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"userId", "restaurantId","type"})
public class DBObject {
    @NonNull
    private String userId;
    @NonNull
    private String restaurantId;
    @NonNull
    private String type;

    public DBObject(@NonNull String userId, @NonNull String restaurantId,@NonNull String type) {
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.type=type;
    }

    public String getUserId() {
        return userId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    @NonNull
    public String getType() {
        return type;
    }

    public void setType(@NonNull String type) {
        this.type = type;
    }
}
