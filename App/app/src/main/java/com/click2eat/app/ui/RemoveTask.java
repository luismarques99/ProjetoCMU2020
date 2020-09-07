package com.click2eat.app.ui;

import android.app.Activity;
import android.os.AsyncTask;

import androidx.room.Room;

import com.click2eat.app.database.DB;
import com.click2eat.app.database.entities.DBObject;
import com.click2eat.app.ui.favorites.FavoritesFragment;
import com.click2eat.app.ui.wishlist.WishlistFragment;

import java.util.List;

public class RemoveTask extends AsyncTask<Void, Void, Void> {
    private DBObject deletedRestaurant;
    private DB db;
    private List<String> list;
    private String type;

    public RemoveTask(DBObject deletedRestaurant, Activity activity, List<String> list, String type) {
        this.deletedRestaurant = deletedRestaurant;
        this.db = Room.databaseBuilder(activity.getApplicationContext(), DB.class, "sample-db").build();
        this.list = list;
        this.type = type;

    }

    @Override
    protected Void doInBackground(Void... voids) {
        while (!isCancelled()) {
            db.daoAccess().delete(deletedRestaurant);
            break;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {

        int index = list.indexOf(deletedRestaurant.getRestaurantId());
        list.remove(deletedRestaurant.getRestaurantId());
        if (type == "wishlist") {
            WishlistFragment.getmAdapter().notifyItemRemoved(index);
            WishlistFragment.getmAdapter().notifyItemRangeChanged(index, list.size());
        }
        if (type == "favorite") {
            FavoritesFragment.getmAdapter().notifyItemRemoved(index);
            FavoritesFragment.getmAdapter().notifyItemRangeChanged(index, list.size());
        }
    }

}

