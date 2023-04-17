package com.priti.Pharmeds;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MedicineActivity extends AppCompatActivity {
    TextView medname,medmfg,meddesc,price,category,mfgdate,expdate,sideeffects;
    Bitmap bitmap;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);
        Toolbar toolbar =findViewById(R.id.toolbar);
        toolbar.setTitle("Product Details");
        AppCompatActivity activity = (AppCompatActivity) this;
        activity.setSupportActionBar(toolbar);
//        ActionBar actionBar = getSupportActionBar();
//        //Actionbar title
//        actionBar.setTitle("Med Detail");
//        //set back button in action bar
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setDisplayShowHomeEnabled(true);
        imageView = findViewById(R.id.medimage);
        medname =findViewById(R.id.textView5);
        medmfg = findViewById(R.id.textView6);
        meddesc = findViewById(R.id.textView7);
        price=findViewById(R.id.textView8);
        category=findViewById(R.id.textView9);
        mfgdate=findViewById(R.id.textView10);
        expdate=findViewById(R.id.textView11);
        sideeffects=findViewById(R.id.textView12);

        byte[] bytes = getIntent().getByteArrayExtra("image");
        Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        medname.setText(getIntent().getStringExtra("medname"));
        medmfg.setText(getIntent().getStringExtra("mfgname"));
        meddesc.setText(getIntent().getStringExtra("meddes"));
        price.setText(getIntent().getStringExtra("price"));
        category.setText(getIntent().getStringExtra("category"));
        mfgdate.setText(getIntent().getStringExtra("mfgdate"));
        expdate.setText(getIntent().getStringExtra("expdate"));
        String sd = getIntent().getStringExtra("sideffect");
        sideeffects.setText(sd);

        imageView.setImageBitmap(bmp);
        bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();


    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
