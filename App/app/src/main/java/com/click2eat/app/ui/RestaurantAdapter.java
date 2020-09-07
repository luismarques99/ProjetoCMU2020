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
import com.click2eat.app.ui.favorites.FavoritesFragment;
import com.click2eat.app.ui.live.LiveFragment;
import com.click2eat.app.ui.visited.VisitedFragment;
import com.click2eat.app.ui.wishlist.WishlistFragment;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {
    private Context mContext;
    private List<Restaurant_> mRestaurants;
    private Activity act;
    private FirebaseAuth mAuth;
    private String currentUserId;
    private View rView;

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
        rView = inflater.inflate(R.layout.item_restaurant, parent, false);

        // Return a new holder instance
        return new RestaurantViewHolder(rView);
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder viewHolder, final int position) {
//        if (position == mRestaurants.size() - 1) {
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
//                    LinearLayout.LayoutParams.WRAP_CONTENT);
//            params.setMargins(0,20,0,20);
//            rView.setLayoutParams(params);
//        }

        // Get the data model based on position
        final Activity activity = act;


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
        addToVisitedButton.getBackground().setTint(activity.getResources().getColor(R.color.colorPrimaryDark));
        CheckTask visitedTask = new CheckTask(activity, currentUserId, restaurant.getId(), "visited", addToVisitedButton,
                VisitedFragment.getVisited(), restaurant.getDistance());
        visitedTask.execute();

        final ImageButton addToFavoritesButton = viewHolder.addToFavoritesButton;
        addToFavoritesButton.getBackground().setTint(activity.getResources().getColor(R.color.colorPrimaryDark));
        CheckTask favTask = new CheckTask(activity, currentUserId, restaurant.getId(), "favorite", addToFavoritesButton,
                FavoritesFragment.getFavorites());
        favTask.execute();

        final ImageButton addToWishlistButton = viewHolder.addToWishlistButton;
        addToWishlistButton.getBackground().setTint(activity.getResources().getColor(R.color.colorPrimaryDark));
        CheckTask wishTask = new CheckTask(activity, currentUserId, restaurant.getId(), "wishlist", addToWishlistButton,
                WishlistFragment.getWishlist());
        wishTask.execute();


       /* addToWishlistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBObject wishlist = new DBObject(currentUserId, restaurant.getId(),"wishlist");
               AddTask wlt = new AddTask(wishlist, activity);
                wlt.execute();
                Toast.makeText(activity, "Added to WishList", Toast.LENGTH_SHORT).show();
            }
        });*/

      /*  final ImageButton addToFavoritesButton = viewHolder.addToFavoritesButton;
        addToFavoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBObject favorite = new DBObject(currentUserId, restaurant.getId(),"favorite");
                AddTask wlt = new AddTask(favorite, activity);
                wlt.execute();
                Toast.makeText(activity, "Added to Favorites", Toast.LENGTH_SHORT).show();

                addToFavoritesButton.getBackground().setTint(activity.getResources().getColor(R.color.red));

            }
        });*/

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

    // TODO: Fazer metodo que ve se o restaurante esta na wishlist ou nao
//    private boolean isInWishlist(int id){
//    }

    private void addToWishlist() {
        Toast.makeText(mContext, "Added to wishlist", Toast.LENGTH_SHORT).show();
    }

    private void removeFromWishlist() {
        Toast.makeText(mContext, "Removed from wishlist", Toast.LENGTH_SHORT).show();
    }

    // TODO: Fazer metodo que ve se o restaurante esta na wishlist ou nao
//    private boolean isInFavorites(int id){
//    }

    private void addToFavorites() {
        Toast.makeText(mContext, "Added to favorites", Toast.LENGTH_SHORT).show();
    }

    private void removeFromFavorites() {
        Toast.makeText(mContext, "Removed from favorites", Toast.LENGTH_SHORT).show();
    }
}
