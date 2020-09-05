package com.click2eat.app.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"userId","restaurantId"})
public class Favorites {
    @NonNull
    private String userId;
    @NonNull
    private String restaurantId;

    public Favorites(@NonNull String userId, @NonNull String restaurantId) {
        this.userId = userId;
        this.restaurantId = restaurantId;
    }

    @NonNull
    public String getUserId() {
        return userId;
    }

    public void setUserId(@NonNull String userId) {
        this.userId = userId;
    }

    @NonNull
    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(@NonNull String restaurantId) {
        this.restaurantId = restaurantId;
    }
}
