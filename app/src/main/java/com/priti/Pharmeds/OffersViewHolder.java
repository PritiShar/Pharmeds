package com.priti.Pharmeds;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class OffersViewHolder extends RecyclerView.ViewHolder{
    View mView;

    public OffersViewHolder(@NonNull View itemView) {
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
    public void setDetails(Context ctx,String title,String description,String fileurl,String percentoff){
        ImageView imageView;
        TextView title1,description1,percentoff1;
        imageView=mView.findViewById(R.id.offerimage);
        title1 = mView.findViewById(R.id.offertitle);
        description1=mView.findViewById(R.id.descriptionoffer);
        percentoff1 = mView.findViewById(R.id.percentoffer);
        title1.setText(title);
       description1.setText(description);
        percentoff1.setText(percentoff);

        Picasso.get().load(fileurl).into(imageView);

    }
    private ViewHolder.ClickListener mClickListener;

    //interface to send callbacks
    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View  view, int position);
    }

    public void setOnClickListener(ViewHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }
}
