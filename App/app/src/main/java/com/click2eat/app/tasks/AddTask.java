package com.click2eat.app.tasks;

import android.app.Activity;
import android.os.AsyncTask;

import androidx.room.Room;

import com.click2eat.app.database.DB;
import com.click2eat.app.database.entities.DBObject;

public class AddTask extends AsyncTask<Void,Void,Void> {
    private DBObject list;
    private DB db;

    public AddTask(DBObject wishlist, Activity activity) {
        this.list=wishlist;
        this.db = Room.databaseBuilder(activity.getApplicationContext(), DB.class, "sample-db").build();

    }

    @Override
    protected Void doInBackground(Void... voids) {
        while (!isCancelled()){
            db.daoAccess().add(list);
            break;
        }
        return null;
    }


}


