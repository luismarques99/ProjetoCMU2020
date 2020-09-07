package com.click2eat.app.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.click2eat.app.R;
import com.click2eat.app.api.RetrofitZomato;
import com.click2eat.app.models.Restaurant_;
import com.click2eat.app.tasks.CheckTask;
import com.click2eat.app.ui.favorites.FavoritesFragment;
import com.click2eat.app.ui.live.LiveFragment;
import com.click2eat.app.ui.visited.VisitedFragment;
import com.click2eat.app.ui.wishlist.WishlistFragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.WishlistViewHolder> {
    private List<String> mRestaurantIds;
    private Activity activity;
    private String currentUserId;
    private FusedLocationProviderClient mFusedLocationClient;

    public SimpleAdapter(List<String> ids, Activity activity, String currentUser) {
        mRestaurantIds = ids;
        this.activity = activity;
        currentUserId = currentUser;
    }


    @Override
    public WishlistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Get layout inflater from context
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate layout
        View rView = inflater.inflate(R.layout.item_restaurant, parent, false);

        // Return a new holder instance
        return new WishlistViewHolder(rView);
    }

    @Override
    public void onBindViewHolder(final WishlistViewHolder viewHolder, final int position) {
        // Get the data model based on position
        final String id = mRestaurantIds.get(position);

        RetrofitZomato.getApi().getRestaurantDetails(Integer.parseInt(id), activity.getResources().getString(R.string.user_key))
                .enqueue(new Callback<Restaurant_>() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void onResponse(Call<Restaurant_> call, final Response<Restaurant_> response) {
                        final TextView name = viewHolder.nameTextView;
                        name.setText(response.body().getName());
                        final TextView rating = viewHolder.ratingTextView;
                        rating.setText(response.body().getUserRating().getAggregateRating());

                        final ImageButton wishlistButton = viewHolder.wishlistButton;
                        wishlistButton.getBackground().setTint(activity.getResources().getColor(R.color.colorPrimaryDark));
                        CheckTask wishTask = new CheckTask(activity, currentUserId, id, "wishlist", wishlistButton,
                                WishlistFragment.getWishlist());
                        wishTask.execute();

                        final ImageButton favoritesButton = viewHolder.favoritesButton;
                        favoritesButton.getBackground().setTint(activity.getResources().getColor(R.color.colorPrimaryDark));
                        CheckTask favTask = new CheckTask(activity, currentUserId, id, "favorite", favoritesButton,
                                FavoritesFragment.getFavorites());
                        favTask.execute();

                        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                LiveFragment.getListener().onRestaurantClicked(id);
                            }
                        });

                        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);
                        mFusedLocationClient.getLastLocation().addOnSuccessListener(activity, new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(final Location location) {
                                if (location != null) {
                                    final TextView mDistance = viewHolder.distanceTextView;
                                    double distance = calculateDistance(Double.parseDouble(response.body().getLocation().getLatitude()),
                                            Double.parseDouble(response.body().getLocation().getLongitude()),
                                            location.getLatitude(), location.getLongitude());
                                    distance = (double) Math.round(distance * 100d) / 100d;
                                    mDistance.setText(distance + "Km");
                                    final ImageButton visitedButton = viewHolder.visitedButton;
                                    visitedButton.getBackground().setTint(activity.getResources().getColor(R.color.colorPrimaryDark));
                                    CheckTask visitedTask = new CheckTask(activity, currentUserId, id, "visited", visitedButton,
                                            VisitedFragment.getVisited(),
                                            distance);
                                    visitedTask.execute();
                                }
                            }
                        }).addOnFailureListener(activity, new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(activity, "It wasn't possible to determine your location", Toast.LENGTH_SHORT).show();
                            }
                        });


                    }

                    @Override
                    public void onFailure(Call<Restaurant_> call, Throwable t) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        builder.setMessage("Couldn't load restaurant details");
                        AlertDialog mDialog = builder.create();
                        mDialog.show();
                    }
                });

    }

    @Override
    public int getItemCount() {
        return mRestaurantIds.size();
    }

    private double calculateDistance(double latRestaurant, double lonRestaurant, double myLat, double myLon) {
        if ((myLat == latRestaurant) && (myLon == lonRestaurant)) {
            return 0;
        } else {
            double theta = myLon - lonRestaurant;
            double dist = Math.sin(Math.toRadians(myLat)) * Math.sin(Math.toRadians(latRestaurant))
                    + Math.cos(Math.toRadians(myLat)) * Math.cos(Math.toRadians(latRestaurant)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            dist = dist * 1.609344;
            return dist;
        }
    }

    public class WishlistViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView ratingTextView;
        public TextView distanceTextView;
        public ImageButton wishlistButton;
        public ImageButton favoritesButton;
        public ImageButton visitedButton;

        public WishlistViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.restaurantName);
            ratingTextView = itemView.findViewById(R.id.restaurantRating);
            distanceTextView = itemView.findViewById(R.id.restaurantDistance);
            wishlistButton = itemView.findViewById(R.id.button_wishlist);
            favoritesButton = itemView.findViewById(R.id.button_favorites);
            visitedButton = itemView.findViewById(R.id.button_visited);
        }

    }

}
