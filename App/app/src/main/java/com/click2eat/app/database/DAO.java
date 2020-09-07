package com.click2eat.app.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.click2eat.app.database.entities.DBObject;

@Dao
public interface DAO {
    @Insert
    public void add(DBObject object);

    @Delete
    public void delete(DBObject object);

    @Query("Select restaurantId From DBObject Where userId=:id AND type=:mType")
    public String[] loadList(String id,String mType);


    @Query("SELECT COUNT(*) FROM DBObject WHERE userId = :uId AND restaurantId=:rId AND type=:mType")
    int count(String uId,String rId,String mType);









}
