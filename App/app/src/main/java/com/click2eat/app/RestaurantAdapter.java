package com.click2eat.app;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.click2eat.app.models.Restaurant_;
import com.click2eat.app.ui.live.LiveFragment;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {
    private Context mContext;
    private List<Restaurant_> mRestaurants;
    private Activity act;
    private String currentUserId;

    public RestaurantAdapter(Context context, List<Restaurant_> restaurants, Activity activity) {
        mRestaurants = restaurants;
        mContext = context;
//        currentUserId = userId;
        act = activity;
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
        final Activity activity = act;
        final Restaurant_ restaurant = mRestaurants.get(position);
        final TextView name = viewHolder.nameTextView;
        name.setText(restaurant.getName());
        final TextView rating = viewHolder.ratingTextView;
        rating.setText((restaurant.getUserRating().getAggregateRating()));
        final TextView distance = viewHolder.distanceTextView;
        distance.setText(String.valueOf(restaurant.getDistance()) + " Km");
//        final ImageButton wishlist = viewHolder.wishlistButton;
//        wishlist.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Wishlist wishlist = new Wishlist(currentUserId, restaurant.getId());
//                AddWLTask wlt = new AddWLTask(wishlist, activity);
//                wlt.execute();
//                Toast.makeText(mContext, "Restaurant added to wishlist", Toast.LENGTH_SHORT).show();
//
//
//            }
//        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LiveFragment.getListener().onRestaurantClicked(restaurant.getId());
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
        public ImageButton wishlistButton;

        public RestaurantViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.restaurantName);
            ratingTextView = itemView.findViewById(R.id.restaurantRating);
            distanceTextView = itemView.findViewById(R.id.restaurantDistance);
//            wishlistButton = itemView.findViewById(R.id.wishlistButton);
        }

    }

}
