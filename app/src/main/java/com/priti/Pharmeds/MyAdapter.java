package com.priti.Pharmeds;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

   private Context context;
   private List<String> titles;
   private  List<Integer> images;
   private MyAdapter adapter;
   public MyAdapter(Context context,List<String>titles,List<Integer>images){
       this.context=context;
       this.titles=titles;
       this.images=images;
   }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.grid_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       holder.textView.setText(titles.get(position));
       holder.imageView.setImageResource(images.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = holder.getAbsoluteAdapterPosition();
                switch(num){
                    case 0:
                        Intent intent =  new Intent(context,Medicinerecyclerview.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("covien","Covid Essentials");
                        context.startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(context,Medicinerecyclerview.class);
                        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent1.putExtra("personalcare","Personal care");
                        context.startActivity(intent1);
                        break;
                    case 7:
                        Intent intent2 = new Intent(context,Medicinerecyclerview.class);
                        intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent2.putExtra("allopathic","Allopathic");
                        context.startActivity(intent2);
                        break;
                    case 6:
                        Intent intent3 = new Intent(context,Medicinerecyclerview.class);
                        intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent3.putExtra("diabetic","Diabetic");
                        context.startActivity(intent3);
                        break;
                    case 3:
                        Intent intent4 = new Intent(context,Medicinerecyclerview.class);
                        intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent4.putExtra("beauty","Beauty");
                        context.startActivity(intent4);
                        break;
                    case 4:
                        Intent intent5 = new Intent(context,Medicinerecyclerview.class);
                        intent5.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent5.putExtra("skincare","Skin care");
                        context.startActivity(intent5);
                        break;
                    case 5:
                        Intent intent6 = new Intent(context,Medicinerecyclerview.class);
                        intent6.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent6.putExtra("homecare","Home care");
                        context.startActivity(intent6);
                        break;
                    case 2:
                        Intent intent7 = new Intent(context,Medicinerecyclerview.class);
                        intent7.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent7.putExtra("healthydrinks","Healthy Drinks");
                        context.startActivity(intent7);
                        break;
                    case 13:
                        Intent intent8 = new Intent(context,Medicinerecyclerview.class);
                        intent8.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent8.putExtra("ayurvedic","Ayurvedic");
                        context.startActivity(intent8);
                        break;
                    case 12:
                        Intent intent9 = new Intent(context,Medicinerecyclerview.class);
                        intent9.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent9.putExtra("accessories","Accessories");
                        context.startActivity(intent9);
                        break;
                    case 8:
                        Intent intent10 = new Intent(context,Medicinerecyclerview.class);
                        intent10.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent10.putExtra("babycare","Baby Care");
                        context.startActivity(intent10);
                        break;
                    case 9:
                        Intent intent11 = new Intent(context,Medicinerecyclerview.class);
                        intent11.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent11.putExtra("womenscare","Womens care");
                        context.startActivity(intent11);
                        break;
                    case 10:
                        Intent intent12 = new Intent(context,Medicinerecyclerview.class);
                        intent12.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent12.putExtra("menscare","Mens care");
                        context.startActivity(intent12);
                        break;
                    case 11:
                        Intent intent13 = new Intent(context,Medicinerecyclerview.class);
                        intent13.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent13.putExtra("elderlycare","Elderly care");
                        context.startActivity(intent13);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
       ImageView imageView;
       TextView textView;
       public MyViewHolder(@NonNull View itemView){
            super(itemView);
            imageView=itemView.findViewById(R.id.imageview);
            textView=itemView.findViewById(R.id.textcategory);
        }

    }

}
