package com.priti.Pharmeds;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class prescription extends AppCompatActivity {
    private final int Gallery_REQ_Code = 1000;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription);
        imageView= findViewById(R.id.imggallery);
        Button btn=findViewById(R.id.btncamera);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent igallery=new Intent(Intent.ACTION_PICK);
                igallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(igallery,Gallery_REQ_Code);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestcode, int resultcode, @Nullable Intent data) {

        super.onActivityResult(requestcode, resultcode, data);
        if(resultcode==RESULT_OK){
            if(requestcode==Gallery_REQ_Code){
             imageView.setImageURI(data.getData());
            }
        }
    }
}