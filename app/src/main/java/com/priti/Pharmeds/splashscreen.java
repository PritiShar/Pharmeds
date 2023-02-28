package com.priti.Pharmeds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.window.SplashScreen;

import com.airbnb.lottie.LottieAnimationView;

public class splashscreen extends AppCompatActivity {
    ImageView imageView;
    LottieAnimationView lottie;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        imageView=findViewById(R.id.background);
        lottie = findViewById(R.id.lottie1);
        textView=findViewById(R.id.text);

//        imageView.animate().;
//        textView.animate().translationY(1600).setDuration(1000).setStartDelay(6500);
//        lottie.animate().translationY(1600).setDuration(1000).setStartDelay(6500);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent i = new Intent(splashscreen.this, Appintro.class);
                startActivity(i);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finish();
            }
        }, 3000);
            }
        }


