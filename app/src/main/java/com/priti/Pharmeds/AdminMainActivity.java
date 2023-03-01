package com.priti.Pharmeds;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class AdminMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        ChipNavigationBar chipNavigationBar;
        chipNavigationBar = findViewById(R.id.bottom_menu1);
        chipNavigationBar.setItemSelected(R.id.home1, true);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout1, new HomeAdminFragment()).commit();
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onItemSelected(int i) {
                switch (i) {
                    case R.id.home1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout1, new HomeAdminFragment()).commit();
                        break;
                    case R.id.order1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout1, new OrdersAdminFragment()).commit();
                        break;
                    case R.id.profile1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout1, new ProfileAdminFragment()).commit();
                        break;

                }
            }
        });

    }
}
