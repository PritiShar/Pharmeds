package com.priti.Pharmeds;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Homefragment extends Fragment {

    private ImageButton shopping;
    private ImageSlider imageSlider;
    private RecyclerView recyclerView;
    private List<String> title;
    private List<Integer>images;
    Button upload;


    RecyclerView topProducts,HealthLibraryrecyclerview;
    List<HealthLibraryModel> healthLibraryModelList;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;


    public Homefragment() {
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
        View v=inflater.inflate(R.layout.fragment_homefragment, container, false);
        imageSlider = v.findViewById(R.id.image_slider);
        ArrayList<SlideModel> slideModels=new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.imageslider1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.imageslider2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.imageslider3,  ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.imageslider4, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.imageslider5, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.imageslider6,  ScaleTypes.FIT));

        imageSlider.setImageList(slideModels,ScaleTypes.FIT);

        recyclerView=v.findViewById(R.id.recyclerview);

        title=new ArrayList<>();
        images=new ArrayList<>();
        images.add(R.drawable.dettol);
        images.add(R.drawable.personalcare);
        images.add(R.drawable.horlicks);
        images.add(R.drawable.nailpolish);
        images.add(R.drawable.nivea);
        images.add(R.drawable.harpic);
        images.add(R.drawable.himalaya);
        images.add(R.drawable.mask);
        images.add(R.drawable.diapers);
        images.add(R.drawable.pads);
        images.add(R.drawable.menscare);
        images.add(R.drawable.adultdiaper);

        title.add("Covid Essentials");
        title.add("Personal Care");
        title.add("Health Drinks");
        title.add("Beauty");
        title.add("Skin Care");
        title.add("Home Care");
        title.add("Ayurveda Care");
        title.add("Accessories");
        title.add("Baby care");
        title.add("Womens Care");
        title.add("Mens care");
        title.add("Elderly Care");

        MyAdapter adapter = new MyAdapter(getActivity(), title, images);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),3,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        topProducts = v.findViewById(R.id.Topproductsrecyclerview);
//        topProducts.setHasFixedSize(true);
        topProducts.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        List<topproductsmodelclass> imageList = new ArrayList<>();
        imageList.add(new topproductsmodelclass(R.drawable.menscare,"Add to cart"));
        imageList.add(new topproductsmodelclass(R.drawable.personalcare,"Add to cart"));
        imageList.add(new topproductsmodelclass(R.drawable.benadryl,"Add to cart"));
        imageList.add(new topproductsmodelclass(R.drawable.evion400,"Add to cart"));
        imageList.add(new topproductsmodelclass(R.drawable.crocin,"Add to cart"));
        topproductsAdapter topproductsAdapter=new topproductsAdapter(imageList);
        topProducts.setAdapter(topproductsAdapter);

        HealthLibraryrecyclerview = v.findViewById(R.id.HealthLibrary);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        HealthLibraryrecyclerview.setLayoutManager(linearLayoutManager1);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setView(R.layout.progess_layout);
        AlertDialog dialog=builder.create();
        dialog.show();

        healthLibraryModelList=new ArrayList<>();
        healthlibraryAdapter adapter1 = new healthlibraryAdapter(getActivity(),healthLibraryModelList);
        HealthLibraryrecyclerview.setAdapter(adapter1);
        databaseReference = FirebaseDatabase.getInstance().getReference("HealthLibrary");
        dialog.show();


        eventListener=databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                healthLibraryModelList.clear();
                for(DataSnapshot itemSnapshot: snapshot.getChildren()){
                    HealthLibraryModel healthLibraryModel=itemSnapshot.getValue(HealthLibraryModel.class);
                    healthLibraryModelList.add(healthLibraryModel);
                }
                adapter1.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
             dialog.dismiss();
            }
        });
//        HealthLibrary = v.findViewById(R.id.HealthLibrary);
//        HealthLibrary.setHasFixedSize(true);
//        HealthLibrary.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
//        List<Healthlibrarymodelclass> imageList1 = new ArrayList<>();
//        imageList1.add(new Healthlibrarymodelclass(R.drawable.depression,"Depression leads to ..."));
//        imageList1.add(new Healthlibrarymodelclass(R.drawable.pimples,"cure your pimples with..."));
//        imageList1.add(new Healthlibrarymodelclass(R.drawable.homeremedies,"Home remedies to improve blood circulation.."));
//        imageList1.add(new Healthlibrarymodelclass(R.drawable.vegetables,"Diet to be included for Diabetes patients"));
//        healthlibraryAdapter healthadapter=new healthlibraryAdapter(imageList1);
//        HealthLibrary.setAdapter(healthadapter);

        upload=v.findViewById(R.id.upload);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),prescription.class);
                startActivity(i);
            }
        });
      
       
        return v;
    }
}