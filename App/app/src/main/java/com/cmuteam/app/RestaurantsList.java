package com.cmuteam.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestaurantsList extends Fragment {
    private RestaurantAdapter mAdapter;
    private RecyclerView mRecyclerView;
    protected static List<Establishment> restaurantsList;
    private Context context;
    protected static OnRestaurantClickedListener listener;
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        mAuth = FirebaseAuth.getInstance();
        restaurantsList = new ArrayList<>();

        getApi().getNearbyRestaurants("75be9f9e2239fe637bf9cb1b46979d91", 311)
                .enqueue(new Callback<List<Establishment>>() {
                    @Override
                    public void onResponse(Call<List<Establishment>> call, Response<List<Establishment>> response) {
                        List<Establishment> result = response.body();
                        mAdapter = new RestaurantAdapter(context, restaurantsList);
                        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
                        mRecyclerView.addItemDecoration(itemDecoration);
                        for (int i = 0; i < result.size(); i++) {
                            restaurantsList.add(new Establishment(result.get(i).getId(), result.get(i).getName()));
                            mAdapter.notifyItemInserted(i);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Establishment>> call, Throwable t) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("Couldn't find any nearby restaurants");
                        AlertDialog mDialog = builder.create();
                        mDialog.show();


                    }
                });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mContentView = inflater.inflate(R.layout.restaurants_list, container, false);
        mRecyclerView = mContentView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContentView.getContext()));
        mRecyclerView.setAdapter(mAdapter);
        return mContentView;
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (OnRestaurantClickedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnButtonClicked");
        }
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
}

