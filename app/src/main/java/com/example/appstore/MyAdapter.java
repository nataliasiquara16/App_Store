package com.example.appstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appstore.Model.salesmodel;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<salesmodel> list;

    // o adapter é usado para listar os dados e os itens clicados
    public MyAdapter(Context context, ArrayList<salesmodel> list) {
        this.context = context;
        this.list = list;
    }
// método obrigatorio para implementar o RecycleView.Adapter
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }
    // método obrigatorio para implementar o RecycleView.Adapter
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        // para juntar os itens na interface
        salesmodel sale = list.get(position);
        holder.buyer.setText(sale.getBuyer());
        holder.product.setText(sale.getProduct());
        holder.value.setText(sale.getValue());


    }
    // método obrigatorio para implementar o RecycleView.Adapter
    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView buyer,product,value;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            buyer= itemView.findViewById(R.id.tvbuyer);
            product= itemView.findViewById(R.id.tvproduct);
            value=itemView.findViewById(R.id.tvvalue);
        }
    }
}
