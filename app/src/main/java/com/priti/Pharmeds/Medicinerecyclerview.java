package com.priti.Pharmeds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;


public class Medicinerecyclerview extends AppCompatActivity {

    SharedPreferences mSharedPref; //for saving sort settings
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    private FirebaseRecyclerAdapter<ProductModel, ViewHolder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicinerecyclerview);
        Toolbar toolbar =findViewById(R.id.toolbar);
        toolbar.setTitle("Product Details");
        AppCompatActivity activity = (AppCompatActivity) this;
        activity.setSupportActionBar(toolbar);
        mSharedPref = getSharedPreferences("SortSettings", MODE_PRIVATE);
        String mSorting = mSharedPref.getString("Sort", "newest"); //where if no settings is selected newest will be default
        String valuefromcat = getIntent().getStringExtra("covien");
//        LinearLayoutManagerWrapper mLayoutManager = new LinearLayoutManagerWrapper(MainActivity.this);
        LinearLayoutManagerWrapper mLayoutManager =
                new LinearLayoutManagerWrapper(Medicinerecyclerview.this, LinearLayoutManager.VERTICAL, true);

        if (mSorting.equals("newest")) {
            //this will load the items from bottom means newest first
            mLayoutManager.setReverseLayout(true);
            mLayoutManager.setStackFromEnd(true);
        } else if (mSorting.equals("oldest")) {
            //this will load the items from bottom means oldest first
            mLayoutManager.setReverseLayout(false);
            mLayoutManager.setStackFromEnd(false);
        }
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        //set layout as LinearLayout
        mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.setNestedScrollingEnabled(false);

        //send Query to FirebaseDatabase
        if(valuefromcat!=null){
            customAdapterString(valuefromcat);
        }else{
            customAdapterString("Womens care");
        }
    }
    private void customAdapterString(String holder) {
        mRef = FirebaseDatabase.getInstance().getReference("Products").child(holder);
        Query query = mRef.orderByKey();

        FirebaseRecyclerOptions<ProductModel> options = new FirebaseRecyclerOptions.Builder<ProductModel>().
                setQuery(query, ProductModel.class).build();
        adapter = new FirebaseRecyclerAdapter<ProductModel, ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull ProductModel model)
            {
                holder.setDetails(getApplicationContext(), model.getCategories(), model.getMedname(),
                        model.getMfgname(), model.getPrice(),  model.getMfgdate(), model.getExpdate(),
                        model.getMeddescription(), model.getFileurl());
                holder.setOnClickListener(new ViewHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        ImageView imageView;
                        TextView medicineName, medicinePrice, mfgname1, mfgdate1, expdate1, medicinecategory;
                        imageView = view.findViewById(R.id.imageView);
                        medicineName = view.findViewById(R.id.medicine_name);
                        medicinePrice = view.findViewById(R.id.Price);
                        mfgname1 = view.findViewById(R.id.mfgName);
                        mfgdate1 = view.findViewById(R.id.mfgDate);
                        expdate1 = view.findViewById(R.id.expdate);
                        medicinecategory = view.findViewById(R.id.category);
                        Drawable mDrawable = imageView.getDrawable();
                        Bitmap mBitmap = ((BitmapDrawable) mDrawable).getBitmap();
                        String medsid = model.getSideeffects();
                        Intent intent = new Intent(getApplicationContext(), MedicineActivity.class);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        byte[] bytes = stream.toByteArray();
                        intent.putExtra("image", bytes);
                        intent.putExtra("fileurl",model.getFileurl());
                        intent.putExtra("medname", medicineName.getText().toString());
                        intent.putExtra("mfgname", mfgname1.getText().toString());
                        intent.putExtra("meddes", model.getMeddescription());
                        intent.putExtra("price",medicinePrice.getText().toString());
                        intent.putExtra("category",medicinecategory.getText().toString());
                        intent.putExtra("mfgdate",mfgdate1.getText().toString());
                        intent.putExtra("expdate",expdate1.getText().toString());
                        intent.putExtra("sideffect",medsid);
                        startActivity(intent);
                    }
                    @Override
                    public void onItemLongClick(View view, int position) {

                    }
                });
            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
                return new ViewHolder(view);
            }
        };
        adapter.startListening();
        mRecyclerView.setAdapter(adapter);
    }


    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_sort:
                showSortDialog();
                break;
            case R.id.covid:
                //Toast.makeText(this, "Hi", Toast.LENGTH_SHORT).show();
                customAdapterString("Covid Essentials");
                break;
            case R.id.allo:
                customAdapterString("Allopathic");
                break;
            case R.id.diabe:
                customAdapterString("Diabetic care");
                break;
            case R.id.personalcare:
                customAdapterString("Personal care");
                break;
            case R.id.Beauty:
                customAdapterString("Beauty");
                break;
            case R.id.Healthdrinks:
                customAdapterString("Healthy Drinks");
                break;
            case R.id.skincare:
                customAdapterString("Skin care");
                break;
            case R.id.homecare:
                customAdapterString("Home care");
                break;
            case R.id.ayurvedic:
                customAdapterString("Ayurvedic");
                break;
            case R.id.accessories:
                customAdapterString("Accessories");
                break;
            case R.id.babycare:
                customAdapterString("Baby care");
                break;
            case R.id.womenscare:
                customAdapterString("Womens care");
                break;
            case R.id.menscare:
                customAdapterString("Mens care");
                break;
            case R.id.elderlycare:
                customAdapterString("Elderly care");
                break;
            case R.id.medicines:
                customAdapterString("Medicines");
                break;

            default:
                customAdapterString("");
                break;
        }
        //handle other action bar item clicks here
        return super.onOptionsItemSelected(item);
    }

    private void showSortDialog() {
        //options to display in dialog
        String[] sortOptions = {" Newest", " Oldest"};
        //create alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sort by") //set title
                .setIcon(R.drawable.ic_action_sort) //set icon
                .setItems(sortOptions, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position of the selected item
                        // 0 means "Newest" and 1 means "oldest"
                        if (which == 0) {
                            //sort by newest
                            //Edit our shared preferences
                            SharedPreferences.Editor editor = mSharedPref.edit();
                            editor.putString("Sort", "newest"); //where 'Sort' is key & 'newest' is value
                            editor.apply(); // apply/save the value in our shared preferences
                            recreate(); //restart activity to take effect
                        } else if (which == 1) {
                            {
                                //sort by oldest
                                //Edit our shared preferences
                                SharedPreferences.Editor editor = mSharedPref.edit();
                                editor.putString("Sort", "oldest"); //where 'Sort' is key & 'oldest' is value
                                editor.apply(); // apply/save the value in our shared preferences
                                recreate(); //restart activity to take effect
                            }
                        }
                    }
                });
        builder.show();
    }
}