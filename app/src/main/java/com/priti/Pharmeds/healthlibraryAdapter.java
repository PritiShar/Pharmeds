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

public class healthlibraryAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private List<HealthLibraryModel> healthLibraryModelList;

    public healthlibraryAdapter(Context context, List<HealthLibraryModel> healthLibraryModelList) {
        this.context = context;
        this.healthLibraryModelList = healthLibraryModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_healthlibrary,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(healthLibraryModelList.get(position).getFileurl()).into(holder.recImage);
        holder.recTitle.setText(healthLibraryModelList.get(position).getTitle());
        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,detailhealthlibrary.class);
                i.putExtra("Image",healthLibraryModelList.get(holder.getAbsoluteAdapterPosition()).getFileurl());
                i.putExtra("Description",healthLibraryModelList.get(holder.getAbsoluteAdapterPosition()).getDescription());
                i.putExtra("Title",healthLibraryModelList.get(holder.getAbsoluteAdapterPosition()).getTitle());

                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {

        return healthLibraryModelList.size();
    }
}
class MyViewHolder extends RecyclerView.ViewHolder{
    ImageView recImage;
    TextView recTitle;
    CardView recCard;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        recImage=itemView.findViewById(R.id.recimage);
        recTitle = itemView.findViewById(R.id.rectitle);
        recCard = itemView.findViewById(R.id.recyclerviewhealthlibrary);
    }
}