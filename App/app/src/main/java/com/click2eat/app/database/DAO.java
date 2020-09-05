package com.click2eat.app.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.click2eat.app.database.entities.Wishlist;

@Dao
public interface DAO {
    @Insert
    public void addToWishlist(Wishlist wishlist);

    @Delete
    public void deleteFromWishlist(Wishlist wishlist);

    @Query("Select restaurantId From wishlist Where userId=:id")
    public  String[] loadWishlist(String id);
}
