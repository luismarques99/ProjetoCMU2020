package com.click2eat.app.ui;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.click2eat.app.R;
import com.click2eat.app.models.Restaurant_;
import com.click2eat.app.tasks.CheckTask;
import com.click2eat.app.ui.favorites.FavoritesFragment;
import com.click2eat.app.ui.live.LiveFragment;
import com.click2eat.app.ui.visited.VisitedFragment;
import com.click2eat.app.ui.wishlist.WishlistFragment;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {
    private List<Restaurant_> mRestaurants;
    private Activity act;
    private FirebaseAuth mAuth;
    private String currentUserId;
    private View rView;

    public RestaurantAdapter(List<Restaurant_> restaurants, Activity activity) {
        mRestaurants = restaurants;
        act = activity;
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Get layout inflater from context
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate layout
        rView = inflater.inflate(R.layout.item_restaurant, parent, false);

        // Return a new holder instance
        return new RestaurantViewHolder(rView);
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder viewHolder, final int position) {
        final Restaurant_ restaurant = mRestaurants.get(position);

        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();

        final TextView name = viewHolder.nameTextView;
        name.setText(restaurant.getName());

        final TextView rating = viewHolder.ratingTextView;
        rating.setText((restaurant.getUserRating().getAggregateRating()));

        final TextView distance = viewHolder.distanceTextView;
        distance.setText(restaurant.getDistance() + " Km");

        final ImageButton addToVisitedButton = viewHolder.addVisitedButton;
        addToVisitedButton.getBackground().setTint(act.getResources().getColor(R.color.colorPrimaryDark));
        CheckTask visitedTask = new CheckTask(act, currentUserId, restaurant.getId(), "visited", addToVisitedButton,
                VisitedFragment.getVisited(), restaurant.getDistance());
        visitedTask.execute();

        final ImageButton addToFavoritesButton = viewHolder.addToFavoritesButton;
        addToFavoritesButton.getBackground().setTint(act.getResources().getColor(R.color.colorPrimaryDark));
        CheckTask favTask = new CheckTask(act, currentUserId, restaurant.getId(), "favorite", addToFavoritesButton,
                FavoritesFragment.getFavorites());
        favTask.execute();

        final ImageButton addToWishlistButton = viewHolder.addToWishlistButton;
        addToWishlistButton.getBackground().setTint(act.getResources().getColor(R.color.colorPrimaryDark));
        CheckTask wishTask = new CheckTask(act, currentUserId, restaurant.getId(), "wishlist", addToWishlistButton,
                WishlistFragment.getWishlist());
        wishTask.execute();

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
        public ImageButton addToWishlistButton;
        public ImageButton addToFavoritesButton;
        private ImageButton addVisitedButton;

        public RestaurantViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.restaurantName);
            ratingTextView = itemView.findViewById(R.id.restaurantRating);
            distanceTextView = itemView.findViewById(R.id.restaurantDistance);
            addToWishlistButton = itemView.findViewById(R.id.button_wishlist);
            addToFavoritesButton = itemView.findViewById(R.id.button_favorites);
            addVisitedButton = itemView.findViewById(R.id.button_visited);
        }

    }
}
