package com.click2eat.app.ui;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.room.Room;

import com.click2eat.app.database.DB;
import com.click2eat.app.database.entities.DBObject;

public class AddTask extends AsyncTask<Void,Void,Void> {
    private DBObject list;
    private Activity activity;
    private DB db;

    public AddTask(DBObject wishlist, Activity activity) {
        this.list=wishlist;
        this.activity=activity;
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


