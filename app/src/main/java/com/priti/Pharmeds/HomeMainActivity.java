package com.priti.Pharmeds;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class HomeMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);
        ChipNavigationBar chipNavigationBar;
        chipNavigationBar = findViewById(R.id.bottom_menu);
        chipNavigationBar.setItemSelected(R.id.home, true);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new Homefragment()).commit();
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onItemSelected(int i) {
                switch (i) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new Homefragment()).commit();
                        break;
                    case R.id.healthcareproduct:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new healthcarefragment()).commit();
                        break;
                    case R.id.offer:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new offerfragment()).commit();
                        break;
                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new profilefragment()).commit();
                        break;

                }
            }
        });

    }
}