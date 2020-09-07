package com.click2eat.app.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.room.Room;

import com.click2eat.app.R;
import com.click2eat.app.RetrofitZomato;
import com.click2eat.app.database.DB;
import com.click2eat.app.database.entities.DBObject;
import com.click2eat.app.models.Restaurant_;
import com.click2eat.app.ui.favorites.FavoritesFragment;
import com.click2eat.app.ui.wishlist.WishlistFragment;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SendNotificationTask extends AsyncTask<Void, Void, Void> {
    private DB db;
    private Activity activity;
    private String[] ids;
    private String userId;
    private NotificationCompat.Builder mBuilder;
    private Context context;

    public SendNotificationTask(Activity activity, String userId, Context context) {
        this.activity = activity;
        this.userId=userId;
        this.context=context;
        db = Room.databaseBuilder(activity.getApplicationContext(), DB.class, "sample-db").build();


    }

    @Override
    protected Void doInBackground(Void... voids) {
        while (!isCancelled()) {
            ids = db.daoAccess().loadList(userId, "favorite");
            break;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        int position=-1;
        if(ids.length==0){
            mBuilder=new NotificationCompat.Builder(context,"2")
                    .setSmallIcon(R.drawable.ic_place)
                    .setContentTitle("You have no favorites!")
                    .setContentText("Start adding so we can suggest you")
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManagerCompat notificationManager=NotificationManagerCompat.from(context);
            notificationManager.notify(1,mBuilder.build());
            return;

        }
        else if(ids.length==1){
          position=0;
        }
        else{
            Random random = new Random();
            position = random.nextInt(ids.length - 1);
        }


        String id = ids[position];
        RetrofitZomato.getApi().getRestaurantDetails(Integer.parseInt(id), "75be9f9e2239fe637bf9cb1b46979d91")
                .enqueue(new Callback<Restaurant_>() {
                    @Override
                    public void onResponse(Call<Restaurant_> call, Response<Restaurant_> response) {
                        Restaurant_ restaurant=response.body();
                        createNotificationChannel();
                        mBuilder=new NotificationCompat.Builder(context,"1")
                                .setSmallIcon(R.drawable.ic_place)
                                .setContentTitle("Can we make a suggestion?")
                                .setContentText(restaurant.getName()+" is in your favorites")
                                .setAutoCancel(true)
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                        NotificationManagerCompat notificationManager=NotificationManagerCompat.from(context);
                        notificationManager.notify(1,mBuilder.build());


                    }

                    @Override
                    public void onFailure(Call<Restaurant_> call, Throwable t) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        builder.setMessage("Failed");
                        AlertDialog mDialog = builder.create();
                        mDialog.show();
                    }
                });


    }

    private void createNotificationChannel(){
        // Create the NotificationChannel, but only on API 26+ because the NotificationChannel class
        // is a new and not in the support library
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "channel_name";
            String description = "channel description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel("1",name,importance);
            channel.setDescription(description);

            // Register the channel with the System; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }




}
