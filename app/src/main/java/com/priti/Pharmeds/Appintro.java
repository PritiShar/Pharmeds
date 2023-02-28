package com.priti.Pharmeds;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;


public class Appintro extends AppCompatActivity {
    ViewPager mSlideViewPager;
    LinearLayout mDotLayout;
    Button button1,button2,button3;

    TextView dots[];
    ViewPagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appintro);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Appintro.this,Selectuser.class);
                startActivity(i);
                finish();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getItem(0)>0){
                    mSlideViewPager.setCurrentItem(getItem(-1),true);
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getItem(0)<3){
                    mSlideViewPager.setCurrentItem(getItem(1),true);
                }else{
                    Intent i = new Intent(Appintro.this,Selectuser.class);
                    startActivity(i);
                    finish();
                }
            }
        });

        mSlideViewPager=(ViewPager) findViewById(R.id.viewpager);
        mDotLayout =(LinearLayout) findViewById(R.id.indicatorlayout);

        viewPagerAdapter = new ViewPagerAdapter(this);
        mSlideViewPager.setAdapter(viewPagerAdapter);
        setUpindicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);
    }
    public void setUpindicator(int position){
        dots=new TextView[4];
        mDotLayout.removeAllViews();

        for(int i=0 ; i<dots.length; i++){
            dots[i]= new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(ContextCompat.getColor(Appintro.this,R.color.light_blue));
            mDotLayout.addView(dots[i]);
        }
        dots[position].setTextColor(ContextCompat.getColor(Appintro.this,R.color.lighter_blue));
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setUpindicator(position);
            if(position > 0){
                button2.setVisibility(View.VISIBLE);
            }else {
                button2.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    private int getItem(int i){
        return mSlideViewPager.getCurrentItem()+i;
    }
}

