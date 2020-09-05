package com.click2eat.app.ui;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;


import com.click2eat.app.R;
import com.click2eat.app.ZomatoApi;
import com.click2eat.app.models.Restaurant_;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
        getApi().getRestaurantDetails(id, API_KEY)
                .enqueue(new Callback<Restaurant_>() {
                    @Override
                    public void onResponse(Call<Restaurant_> call, Response<Restaurant_> response) {
                        mRestaurantName.setText("Name : " + response.body().getName());
                        mRestaurantAddress.setText("Address : " + response.body().getLocation().getAddress());
                        mRestaurantRating.setText("Rating: " + response.body().getUserRating().getAggregateRating() + ", " + response.body().getUserRating().getRatingText());
                        mVotes.setText("Number of votes : " + response.body().getUserRating().getVotes());
                        mAveragePrice.setText("Average price for two : " + response.body().getAverageCostForTwo() + response.body().getCurrency());
                        mTimings.setText("Timings : " + response.body().getTimings());
                        mPhoneNumbers.setText("Contacts : " + response.body().getPhoneNumbers());
                        mCuisines.setText("Cuisines : " + response.body().getCuisines());
                        List<String> establishmentType = response.body().getEstablishment();

                        String types = "";
                        for (int i = 0; i < establishmentType.size(); i++) {
                            types += establishmentType.get(i) + "\n";
                        }

                        mEstablishment.setText("Establishment type : " + types);

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


    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://developers.zomato.com/api/v2.1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private ZomatoApi getApi() {
        return getRetrofit().create(ZomatoApi.class);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.seeMap) {
//            Intent mapIntent = new Intent(getActivity(), MapActivity.class);
//            startActivity(mapIntent);
        } else if(id == R.id.button_go_back){
            getActivity().onBackPressed();
        }
    }

}
