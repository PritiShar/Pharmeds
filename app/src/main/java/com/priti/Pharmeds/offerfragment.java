package com.priti.Pharmeds;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class offerfragment extends Fragment {

    RecyclerView recyclerviewoffer;
    List<OfferModel> offerModelList;
    ValueEventListener eventListener;
    DatabaseReference databaseReference;

    public offerfragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_offerfragment, container, false);
        recyclerviewoffer=v.findViewById(R.id.offersrecyclerview);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerviewoffer.setLayoutManager(linearLayoutManager1);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setView(R.layout.progess_layout);
        AlertDialog dialog=builder.create();
        dialog.show();

        offerModelList=new ArrayList<>();
        OffersAdapter adapter1 = new OffersAdapter(getActivity(),offerModelList);
        recyclerviewoffer.setAdapter(adapter1);
        databaseReference = FirebaseDatabase.getInstance().getReference("Offers");
        dialog.show();

        eventListener=databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                offerModelList.clear();
                for(DataSnapshot itemSnapshot: snapshot.getChildren()){
                    OfferModel offerModel=itemSnapshot.getValue(OfferModel.class);
                    offerModelList.add(offerModel);
                }
                adapter1.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });
        return v;
    }
}