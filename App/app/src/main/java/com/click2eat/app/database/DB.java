package com.click2eat.app.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.click2eat.app.database.entities.DBObject;

@Database(entities = {DBObject.class},version = 1)
public abstract class DB extends RoomDatabase {

    public abstract DAO daoAccess();
}