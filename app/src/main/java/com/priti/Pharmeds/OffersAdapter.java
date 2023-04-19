package com.priti.Pharmeds;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class OffersAdapter extends RecyclerView.Adapter<MyViewHolder1> {
    private Context context;
    private List<OfferModel> offerModelList;

    public OffersAdapter(Context context, List<OfferModel> offerModelList) {
        this.context = context;
        this.offerModelList = offerModelList;
    }

    @NonNull
    @Override
    public MyViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.offers,parent,false);
        return new MyViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder1 holder, int position) {
        Glide.with(context).load(offerModelList.get(position).getFileurl()).into(holder.upImage);
        holder.upTitle.setText(offerModelList.get(position).getTitle());
        holder.updescription.setText(offerModelList.get(position).getDesc());
        holder.uppercentoff.setText(offerModelList.get(position).getPercentoff());

    }

    @Override
    public int getItemCount() {
        return offerModelList.size();
    }

}
class MyViewHolder1 extends RecyclerView.ViewHolder{
    ImageView upImage;
    TextView upTitle,uppercentoff,updescription;
    CardView upCard;
    public MyViewHolder1(@NonNull View itemView) {
        super(itemView);
        upImage=itemView.findViewById(R.id.offerimage1);
        upTitle = itemView.findViewById(R.id.offertitle1);
        upCard = itemView.findViewById(R.id.cardviewoffer);
        uppercentoff=itemView.findViewById(R.id.percentoffer);
        updescription=itemView.findViewById(R.id.descriptionoffer);
    }
}