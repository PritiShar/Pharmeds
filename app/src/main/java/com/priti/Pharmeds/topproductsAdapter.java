package com.priti.Pharmeds;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class topproductsAdapter extends RecyclerView.Adapter<topproductsAdapter.topproductsViewHolder> {
    public List<topproductsmodelclass> topproductslist;
    public topproductsAdapter(List<topproductsmodelclass> topproductslist){
        this.topproductslist=topproductslist;
    }

    @NonNull
    @Override
    public topproductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_top_products,parent,false);
        return new topproductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull topproductsViewHolder holder, int position) {
        holder.getButton().setText(topproductslist.get(position).getText());
        holder.getImage().setImageResource(topproductslist.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return topproductslist.size();
    }


    public class topproductsViewHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        private Button button;
        public topproductsViewHolder(@NonNull View itemView){
            super(itemView);
            image=itemView.findViewById(R.id.topproduct1);
            button=itemView.findViewById(R.id.button);
        }

        public ImageView getImage() {
            return image;
        }

        public Button getButton() {
            return button;
        }
    }
}
