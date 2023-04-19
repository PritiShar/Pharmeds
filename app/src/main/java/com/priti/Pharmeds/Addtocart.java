package com.priti.Pharmeds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Addtocart extends AppCompatActivity {

    RecyclerView add2crv;
    List<CartModel> cartModelList;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtocart);
        add2crv = findViewById(R.id.add2crv);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        add2crv.setLayoutManager(linearLayoutManager1);
        cartModelList = new ArrayList<>();
        Addtocartadapter addtocartadapter = new Addtocartadapter(getApplicationContext(),cartModelList);
        add2crv.setAdapter(addtocartadapter);
        databaseReference = FirebaseDatabase.getInstance().getReference("Cart");



        eventListener=databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cartModelList.clear();
                for(DataSnapshot itemSnapshot: snapshot.getChildren()){
                    CartModel cartModel=itemSnapshot.getValue(CartModel.class);
                    cartModelList.add(cartModel);
                }
                addtocartadapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}