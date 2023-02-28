package com.priti.Pharmeds;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class healthlibraryAdapter extends RecyclerView.Adapter<healthlibraryAdapter.healthlibraryViewHolder> {
    private List<Healthlibrarymodelclass> healthlibrarylist;

    public healthlibraryAdapter(List<Healthlibrarymodelclass>healthlibrarylist){
        this.healthlibrarylist=healthlibrarylist;
    }

    @NonNull
    @Override
    public healthlibraryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_health_library,parent,false);
        return new healthlibraryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull healthlibraryViewHolder holder, int position) {
        holder.healthtext.setText(healthlibrarylist.get(position).getText());
        holder.imageView.setImageResource(healthlibrarylist.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return healthlibrarylist.size();
    }

    public class healthlibraryViewHolder extends RecyclerView.ViewHolder{
        private TextView healthtext;
        private ImageView imageView;
        public healthlibraryViewHolder(@NonNull View itemView) {
            super(itemView);
            healthtext=itemView.findViewById(R.id.text1health);
            imageView=itemView.findViewById(R.id.healthlibrary1);

        }

}

}
