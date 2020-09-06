package com.click2eat.app.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.AsyncTask;

import androidx.room.Room;

import com.click2eat.app.database.DB;

import java.util.List;

public class LoadListTask extends AsyncTask<Void, Void, Void> {
    private DB db;
    private Activity activity;
    private List<String>list;
    private String[] temp;
    private SimpleAdapter adapter;
    private String id;
    private String type;

    public LoadListTask(Activity activity, List<String> list, SimpleAdapter adapter, String id, String type) {
        this.activity = activity;
        this.list = list;
        this.adapter = adapter;
        this.id = id;
        this.type = type;
        db = Room.databaseBuilder(activity.getApplicationContext(), DB.class, "sample-db").build();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        while (!isCancelled()) {
            this.temp = db.daoAccess().loadList(id, type);
            break;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (temp.length==0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setMessage("You still haven´t add anything");
            AlertDialog mDialog = builder.create();
            mDialog.show();
        } else {
            for (int i = 0; i < temp.length; i++) {
                list.add(temp[i]);
                adapter.notifyItemInserted(i);
            }
        }
    }
}
