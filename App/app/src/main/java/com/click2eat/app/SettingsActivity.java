package com.click2eat.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(this);
        String sort = mSettings.getString("sort", "rating");
        String order = mSettings.getString("order", "desc");
        String radius = mSettings.getString("radius", "10");

        //set Sort enabled
        if (sort.equals("rating")) {
            RadioButton rb = (RadioButton) findViewById(R.id.sort_rating);
            rb.setChecked(true);
        } else if (sort.equals("real_distance")) {
            RadioButton rb = (RadioButton) findViewById(R.id.sort_distance);
            rb.setChecked(true);
        }

        //set Order enabled
        if (order.equals("desc")) {
            RadioButton rb = (RadioButton) findViewById(R.id.order_desc);
            rb.setChecked(true);
        } else if (order.equals("asc")) {
            RadioButton rb = (RadioButton) findViewById(R.id.order_asc);
            rb.setChecked(true);
        }

        //set Radius enabled
        if (radius.equals("10")) {
            RadioButton rb = (RadioButton) findViewById(R.id.radius_10);
            rb.setChecked(true);
        } else if (radius.equals("25")) {
            RadioButton rb = (RadioButton) findViewById(R.id.radius_25);
            rb.setChecked(true);
        } else if (radius.equals("50")) {
            RadioButton rb = (RadioButton) findViewById(R.id.radius_50);
            rb.setChecked(true);
        }

        Button saveButton = findViewById(R.id.button_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToLive = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(goToLive);
            }
        });

    }

    public void onRadioButtonClicked(View v) {
        boolean checked = ((RadioButton) v).isChecked();
        SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(this);

        // Check which radio button was clicked
        switch (v.getId()) {
            case R.id.sort_rating:
                if (checked) {
                    SharedPreferences.Editor mEditor = mSettings.edit();
                    mEditor.putString("sort", "rating");
                    mEditor.commit();

                }
                break;
            case R.id.sort_distance:
                if (checked) {
                    SharedPreferences.Editor mEditor = mSettings.edit();
                    mEditor.putString("sort", "real_distance");
                    mEditor.commit();
                }
                break;
            case R.id.order_desc:
                if (checked) {
                    SharedPreferences.Editor mEditor = mSettings.edit();
                    mEditor.putString("order", "desc");
                    mEditor.commit();

                }
                break;
            case R.id.order_asc:
                if (checked) {
                    SharedPreferences.Editor mEditor = mSettings.edit();
                    mEditor.putString("order", "asc");
                    mEditor.commit();

                }
                break;
            case R.id.radius_10:
                if (checked) {
                    SharedPreferences.Editor mEditor = mSettings.edit();
                    mEditor.putString("radius", "10");
                    mEditor.commit();

                }
                break;
            case R.id.radius_25:
                if (checked) {
                    SharedPreferences.Editor mEditor = mSettings.edit();
                    mEditor.putString("radius", "25");
                    mEditor.commit();

                }
                break;
            case R.id.radius_50:
                if (checked) {
                    SharedPreferences.Editor mEditor = mSettings.edit();
                    mEditor.putString("radius", "50");
                    mEditor.commit();

                }
                break;
        }

    }

    @Override
    public void onClick(View view) {

    }
}