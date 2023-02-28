package com.priti.Pharmeds;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class healthcarefragment extends Fragment {
    private RecyclerView recyclerView;
    private List<String> title;
    private List<Integer>images;

    public healthcarefragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_healthcarefragment, container, false);
        recyclerView=v.findViewById(R.id.recyclerview);

        title=new ArrayList<>();
        images=new ArrayList<>();
        images.add(R.drawable.revitol);
        images.add(R.drawable.pediasure);
        images.add(R.drawable.babyskincare);
        images.add(R.drawable.adultdiaper);
        images.add(R.drawable.shikakaishampoo);
        images.add(R.drawable.vicks);
        images.add(R.drawable.pads);
        images.add(R.drawable.fragnance);
        images.add(R.drawable.thermometer);
        images.add(R.drawable.vissco);
        images.add(R.drawable.oralcare);
        images.add(R.drawable.candid);

        title.add("Vitamins A-Z");
        title.add("Nutritional Drinks");
        title.add("Baby Skin Care");
        title.add("Adult Diapers");
        title.add("Hair Care");
        title.add("Respiratory Care");
        title.add("Womens Care");
        title.add("Fragnance");
        title.add("Thermometera");
        title.add("Mobility & Support");
        title.add("Oral Care");
        title.add("Anti Fungal");

        MyAdapter adapter = new MyAdapter(getActivity(), title, images);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),3,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        return v;

    }
}