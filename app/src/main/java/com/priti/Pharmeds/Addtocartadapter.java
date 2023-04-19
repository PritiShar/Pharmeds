package com.priti.Pharmeds;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Addtocartadapter extends RecyclerView.Adapter<Addtocartadapter.Add2cart> {
    private Context context;
    private List<CartModel> ll;
    public Addtocartadapter(Context context,List<CartModel> ll) {
        this.context = context;
        this.ll = ll;
    }

    @NonNull
    @Override
    public Add2cart onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartrow,parent,false);
        return new Add2cart(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Add2cart holder, int position) {
        Glide.with(context).load(ll.get(position).getFileUrl()).into(holder.im);
        holder.quant.setText(ll.get(position).getQuantity());
        holder.name.setText(ll.get(position).getPname());
        holder.price.setText(ll.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return ll.size();
    }
    class Add2cart extends RecyclerView.ViewHolder{
        TextView quant;
        TextView name;
        TextView price;
        ImageView im;
        public Add2cart(@NonNull View itemView) {
            super(itemView);
            quant = itemView.findViewById(R.id.cquantityname);
            name = itemView.findViewById(R.id.cprodname);
            price = itemView.findViewById(R.id.cpricename);
            im = itemView.findViewById(R.id.cimage);
        }
    }
}
