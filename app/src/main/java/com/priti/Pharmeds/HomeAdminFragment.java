package com.priti.Pharmeds;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class HomeAdminFragment extends Fragment {

    ImageView Categories, HealthLibrary ,Prescriptions, Offers;

    public HomeAdminFragment() {
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
        View v=inflater.inflate(R.layout.fragment_home_admin, container, false);
        Categories=v.findViewById(R.id.categoriesimage);
        Categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),AdminHomePage.class);
                startActivity(intent);
            }
        });

        HealthLibrary =v.findViewById(R.id.HealthLibraryimage);
        HealthLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),AdminHealthLibrary.class);
                startActivity(i);
            }
        });
        return v;


    }
}