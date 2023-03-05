package com.priti.Pharmeds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
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

        FirebaseMessaging.getInstance().subscribeToTopic("General")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Successfull";
                        if (!task.isSuccessful()) {
                            msg = "failed";
                        }
//                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

    }
}