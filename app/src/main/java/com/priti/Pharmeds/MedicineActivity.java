package com.priti.Pharmeds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MedicineActivity extends AppCompatActivity {
    TextView medname,medmfg,meddesc,price,category,mfgdate,expdate,sideeffects;
    Bitmap bitmap;
    ImageView imageView;
    Button addtocart,placenow;
    private DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference("Cart");
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
        Spinner spinner = findViewById(R.id.quantity);
        String qtext = spinner.getSelectedItem().toString();

        imageView = findViewById(R.id.medimage);
        medname =findViewById(R.id.textView5);
        medmfg = findViewById(R.id.textView6);
        meddesc = findViewById(R.id.textView7);
        price=findViewById(R.id.textView8);
        category=findViewById(R.id.textView9);
        mfgdate=findViewById(R.id.textView10);
        expdate=findViewById(R.id.textView11);
        sideeffects=findViewById(R.id.textView12);
        addtocart=findViewById(R.id.addtocart);
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
        String fileurl = getIntent().getStringExtra("fileurl");
        String pname = getIntent().getStringExtra("medname");
        String preprodname = pname;
        String price = getIntent().getStringExtra("price");
        sideeffects.setText(sd);

        imageView.setImageBitmap(bmp);
        bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //existed node update
//                FirebaseDatabase.getInstance().getReference("Cart")
//                        .orderByChild("pname")
//                        .equalTo(pname)
//                        .addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                for (DataSnapshot cartItemSnapshot : snapshot.getChildren()) {
//                                    String cartuid = cartItemSnapshot.getKey();
//                                    updateCartItemQuantity(cartuid, qtext); // replace with the new quantity
//                                }
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError error) {
//                                // query cancelled or failed
//                            }
//                        });
                //new node add
                CartModel cm = new CartModel(fileurl,pname,qtext,price);
                String cartuid = mDatabase.push().getKey();
                mDatabase.child(cartuid).setValue(cm);
                Toast.makeText(activity, "Added successfully to cart", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void updateCartItemQuantity(String cartuid, String newQuantity) {
        DatabaseReference cartItemRef = FirebaseDatabase.getInstance().getReference("Cart").child(cartuid);
        cartItemRef.child("quantity").setValue(String.valueOf(newQuantity))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // update successful
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // update failed
                    }
                });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
