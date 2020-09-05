package com.click2eat.app.ui;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.click2eat.app.R;
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

        final ImageButton addToWishlistButton = viewHolder.addToWishlistButton;
        addToWishlistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Wishlist wishlist = new Wishlist(currentUserId, restaurant.getId());
//                AddWLTask wlt = new AddWLTask(wishlist, activity);
//                wlt.execute();
                addToWishlistButton.getBackground().setTint(activity.getResources().getColor(R.color.green));
                addToWishlist();
            }
        });

        final ImageButton addToFavoritesButton = viewHolder.addToFavoritesButton;
        addToFavoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToFavoritesButton.getBackground().setTint(activity.getResources().getColor(R.color.red));
                addToFavorites();
            }
        });

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

        public RestaurantViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.restaurantName);
            ratingTextView = itemView.findViewById(R.id.restaurantRating);
            distanceTextView = itemView.findViewById(R.id.restaurantDistance);
            addToWishlistButton = itemView.findViewById(R.id.button_add_wishlist);
            addToFavoritesButton = itemView.findViewById(R.id.button_add_favorites);
        }

    }

    // TODO: Fazer metodo que ve se o restaurante esta na wishlist ou nao
//    private boolean isInWishlist(int id){
//    }

    private void addToWishlist(){
        Toast.makeText(mContext, "Added to wishlist", Toast.LENGTH_SHORT).show();
    }

    private void removeFromWishlist(){
        Toast.makeText(mContext, "Removed from wishlist", Toast.LENGTH_SHORT).show();
    }

    // TODO: Fazer metodo que ve se o restaurante esta na wishlist ou nao
//    private boolean isInFavorites(int id){
//    }

    private void addToFavorites(){
        Toast.makeText(mContext, "Added to favorites", Toast.LENGTH_SHORT).show();
    }

    private void removeFromFavorites(){
        Toast.makeText(mContext, "Removed from favorites", Toast.LENGTH_SHORT).show();
    }
}
