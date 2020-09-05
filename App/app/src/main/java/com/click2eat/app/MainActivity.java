package com.click2eat.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.click2eat.app.ui.OnRestaurantClickedListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements OnRestaurantClickedListener {

    private FirebaseAuth mAuth;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbar.inflateMenu(R.menu.app_bar_options);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        // LiveFragment
//        LiveFragment liveFragment = new LiveFragment();
//        Bundle args = new Bundle();
//        args.putString("userId", currentUser.getUid());
//        liveFragment.setArguments(args);
//
//        fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.nav_host_fragment, liveFragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.app_bar_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.options_profile:
                return true;

            case R.id.options_settings:
                return true;

            case R.id.options_logout:
                logOut();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onRestaurantClicked(String id) {
//        RestaurantDetailsFragment restaurantDetails = new RestaurantDetailsFragment();
        Bundle args = new Bundle();
        args.putString("id", id);
//        restaurantDetails.setArguments(args);
//        fragmentManager = getSupportFragmentManager();
//        fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.nav_host_fragment, restaurantDetails);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
        navController.navigate(R.id.restaurant_details, args);
    }

    private void logOut() {
        mAuth.signOut();
        Intent backToLoginIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(backToLoginIntent);
    }

}