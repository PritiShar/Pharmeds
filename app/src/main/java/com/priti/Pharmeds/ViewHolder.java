package com.priti.Pharmeds;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder{

    View mView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        mView = itemView;

        //item click
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view, getBindingAdapterPosition());
            }
        });
        //item long click
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.onItemLongClick(view, getBindingAdapterPosition());
                return true;
            }
        });

    }

    //set details to recycler view row
    public void setDetails(Context ctx, String categories ,String medname, String mfgname, String price, String mfgdate, String expdate, String meddescription, String fileurl){
        ImageView imageView;
        TextView medicineName, medicinePrice, mfgname1, mfgdate1, expdate1,medicinecategory;
        imageView = mView.findViewById(R.id.imageView);
        medicineName = mView.findViewById(R.id.medicine_name);
        medicinePrice = mView.findViewById(R.id.Price);
        mfgname1 = mView.findViewById(R.id.mfgName);
        mfgdate1 = mView.findViewById(R.id.mfgDate);
        expdate1 = mView.findViewById(R.id.expdate);
        medicinecategory = mView.findViewById(R.id.category);
        medicineName.setText(medname);
        medicinePrice.setText(price);
        mfgdate1.setText(mfgdate);
        expdate1.setText(expdate);
        mfgname1.setText(mfgname);
        medicinecategory.setText(categories);
        Picasso.get().load(fileurl).into(imageView);

    }

    private ClickListener mClickListener;

    //interface to send callbacks
    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View  view, int position);
    }

    public void setOnClickListener(ClickListener clickListener){
        mClickListener = clickListener;
    }
}