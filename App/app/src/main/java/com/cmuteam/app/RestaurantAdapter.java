package com.cmuteam.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.cmuteam.app.Models.Restaurant;
import com.cmuteam.app.Models.Restaurant_;
import com.cmuteam.app.Models.UserRating;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {
    private Context mContext;
    private List<Restaurant_> mRestaurants;

    public RestaurantAdapter(Context context, List<Restaurant_> restaurants) {
        mRestaurants = restaurants;
        mContext = context;
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Get layout inflater from context
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate layout
        View rView = inflater.inflate(R.layout.item_restaurant, parent, false);

        // Return a new holder instance
        return new RestaurantViewHolder(rView);
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder viewHolder, final int position) {
        // Get the data model based on position
        final Restaurant_ restaurant = mRestaurants.get(position);
        final TextView name=viewHolder.nameTextView;
        name.setText(restaurant.getName());
        final TextView rating=viewHolder.ratingTextView;
        rating.setText((restaurant.getUserRating().getAggregateRating()));
        final TextView distance= viewHolder.distanceTextView;
        distance.setText(String.valueOf(restaurant.getDistance())+" Km");
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RestaurantsList.listener.onRestaurantClicked(position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return mRestaurants.size();
    }



    public class RestaurantViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView ratingTextView;
        public TextView distanceTextView;

        public RestaurantViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.restaurantName);
            ratingTextView=itemView.findViewById(R.id.restaurantRating);
            distanceTextView=itemView.findViewById(R.id.restaurantDistance);

        }

    }

}
