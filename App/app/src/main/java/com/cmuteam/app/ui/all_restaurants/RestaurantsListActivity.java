package com.cmuteam.app.ui.all_restaurants;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;

import com.cmuteam.app.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RestaurantsListActivity extends AppCompatActivity implements OnRestaurantClickedListener {
    private FirebaseAuth mAuth;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private static final int REQUEST_FINE_LOCATION = 100;
    private LocationRequest mLocationRequest;
    private LocationCallback mLocationCallback;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants_list);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        context=this;

        Toolbar myToolbar = findViewById(R.id.toolbarRestaurantsList);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Nearby Restaurants");

        RestaurantsListFragment rList = new RestaurantsListFragment();
        Bundle args = new Bundle();
        rList.setArguments(args);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, rList);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


    }

    @Override
    public void onRestaurantClicked(int position) {

    }


}