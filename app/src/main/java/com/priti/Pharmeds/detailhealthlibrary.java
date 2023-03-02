package com.priti.Pharmeds;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class detailhealthlibrary extends AppCompatActivity {
    TextView detaildesc , detailtitle;
    ImageView detailimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailhealthlibrary);
        detaildesc=findViewById(R.id.detaildesc);
        detailimage=findViewById(R.id.detailimage);
        detailtitle=findViewById(R.id.detailtitle);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            detaildesc.setText(bundle.getString("Description"));
            detailtitle.setText(bundle.getString("Title"));
            Glide.with(this).load(bundle.getString("Image")).into(detailimage);
        }
    }
}