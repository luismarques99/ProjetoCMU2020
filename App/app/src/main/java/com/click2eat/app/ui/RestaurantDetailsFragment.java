package com.click2eat.app.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


import com.click2eat.app.MapActivity;
import com.click2eat.app.R;
import com.click2eat.app.api.RetrofitZomato;
import com.click2eat.app.models.Restaurant_;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantDetailsFragment extends Fragment implements View.OnClickListener {
    private final String API_KEY = "75be9f9e2239fe637bf9cb1b46979d91";
    private TextView mRestaurantName;
    private TextView mRestaurantAddress;
    private TextView mRestaurantRating;
    private TextView mVotes;
    private TextView mAveragePrice;
    private TextView mTimings;
    private TextView mPhoneNumbers;
    private TextView mCuisines;
    private TextView mEstablishment;
    private Button mMapButton;
    private Button backButton;
    private String lat;
    private String lon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mContentView = inflater.inflate(R.layout.fragment_restaurant_details, container, false);
        int id = Integer.parseInt(getArguments().getString("id"));
        mRestaurantName = mContentView.findViewById(R.id.restaurantName);
        mRestaurantAddress = mContentView.findViewById(R.id.restaurantAddress);
        mRestaurantRating = mContentView.findViewById(R.id.restaurantRating);
        mVotes = mContentView.findViewById(R.id.votes);
        mAveragePrice = mContentView.findViewById(R.id.averagePrice);
        mTimings = mContentView.findViewById(R.id.timings);
        mPhoneNumbers = mContentView.findViewById(R.id.phoneNumbers);
        mCuisines = mContentView.findViewById(R.id.cuisines);
        mEstablishment = mContentView.findViewById(R.id.establishmentTypes);
        mMapButton = mContentView.findViewById(R.id.seeMap);
        mMapButton.setOnClickListener(this);
        backButton = mContentView.findViewById(R.id.button_go_back);
        backButton.setOnClickListener(this);

        getDetails(id);

        return mContentView;
    }


    private void getDetails(int id) {
        RetrofitZomato.getApi().getRestaurantDetails(id, API_KEY)
                .enqueue(new Callback<Restaurant_>() {
                    @Override
                    public void onResponse(Call<Restaurant_> call, Response<Restaurant_> response) {
                        mRestaurantName.setText(response.body().getName());
                        mRestaurantAddress.setText(response.body().getLocation().getAddress());
                        mRestaurantRating.setText(response.body().getUserRating().getAggregateRating() + ", " + response.body().getUserRating().getRatingText());
                        mVotes.setText(response.body().getUserRating().getVotes());
                        mAveragePrice.setText(response.body().getAverageCostForTwo() + response.body().getCurrency());
                        mTimings.setText(response.body().getTimings());
                        mPhoneNumbers.setText(response.body().getPhoneNumbers());
                        mCuisines.setText(response.body().getCuisines());
                        List<String> establishmentType = response.body().getEstablishment();
                        mEstablishment.setText(establishmentType.size() != 0 ? establishmentType.get(0) : "N/a");
                        lat=response.body().getLocation().getLatitude();
                        lon=response.body().getLocation().getLongitude();

                    }

                    @Override
                    public void onFailure(Call<Restaurant_> call, Throwable t) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("Couldn't load restaurant details");
                        AlertDialog mDialog = builder.create();
                        mDialog.show();
                    }
                });
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.seeMap) {
            Intent mapIntent = new Intent(getActivity(), MapActivity.class);
            mapIntent.putExtra("lat",lat);
            mapIntent.putExtra("lon",lon);
            startActivity(mapIntent);
        } else if (id == R.id.button_go_back) {
            getActivity().onBackPressed();
        }
    }

}
