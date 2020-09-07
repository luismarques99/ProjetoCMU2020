package com.click2eat.app.ui;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.room.Room;

import com.click2eat.app.R;
import com.click2eat.app.database.DB;
import com.click2eat.app.database.entities.DBObject;

import java.util.List;

public class CheckTask extends AsyncTask<Void, Void, Void> {
    private DB db;
    private Activity activity;
    private String userId;
    private String restaurantId;
    private String type;
    private ImageButton button;
    private List<String> list;
    private Double distance;
    private int count;

    public CheckTask(Activity activity, String idUser, String idRestaurant, String type, ImageButton button, List<String> list) {
        this.activity = activity;
        this.userId = idUser;
        this.restaurantId = idRestaurant;
        this.type = type;
        this.button = button;
        this.list = list;
        this.distance = null;
        db = Room.databaseBuilder(activity.getApplicationContext(), DB.class, "sample-db").build();

    }

    public CheckTask(Activity activity, String userId, String restaurantId, String type, ImageButton button, List<String> list, Double distance) {
        this.activity = activity;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.type = type;
        this.button = button;
        this.list = list;
        this.distance = distance;
        db = Room.databaseBuilder(activity.getApplicationContext(), DB.class, "sample-db").build();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        while (!isCancelled()) {
            count = db.daoAccess().count(userId, restaurantId, type);
            break;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {

        //If it doesn´t exist in the database
        if (count == 0) {

            //to add to favorites and wishlist it isn´t required to check if the user is close
            if (type != "visited") {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DBObject object = new DBObject(userId, restaurantId, type);
                        AddTask wlt = new AddTask(object, activity);
                        wlt.execute();
                        if (type == "wishlist") {
                            button.getBackground().setTint(activity.getResources().getColor(R.color.green));
                            Toast.makeText(activity, "Added to Wishlist", Toast.LENGTH_SHORT).show();
                        }
                        if (type == "favorite") {
                            button.getBackground().setTint(activity.getResources().getColor(R.color.red));
                            Toast.makeText(activity, "Added to Favorites", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            } else {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (distance > 0.10) {
                            Toast.makeText(activity, "You need to be in a radius of 100 meters to do this operation", Toast.LENGTH_SHORT).show();
                        } else {
                            DBObject object = new DBObject(userId, restaurantId, type);
                            AddTask at = new AddTask(object, activity);
                            at.execute();
                            Toast.makeText(activity, "Marked as visited", Toast.LENGTH_SHORT).show();
                            button.getBackground().setTint(activity.getResources().getColor(R.color.green));
                        }


                    }
                });
            }
        }

        //If exists

        else {
            if (type == "wishlist" || type == "visited") {
                button.getBackground().setTint(activity.getResources().getColor(R.color.green));
            }
            if (type == "favorite") {
                button.getBackground().setTint(activity.getResources().getColor(R.color.red));
            }

            //In our bussiness logic we don´t allow the user to remove a place from the visited list
            //so we only add a click listener event in case the object hasn´t a type equal to visited
            if (type != "visited") {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DBObject object = new DBObject(userId, restaurantId, type);
                        RemoveTask rt = new RemoveTask(object, activity, list, type);
                        rt.execute();
                        button.getBackground().setTint(activity.getResources().getColor(R.color.colorPrimaryDark));
                    }
                });
            }
        }
    }
}