package com.example.foodheroes.Adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodheroes.Models.Mitra;
import com.example.foodheroes.R;

import java.util.ArrayList;
import java.util.List;

public class MitraAdapter extends RecyclerView.Adapter<MitraAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Mitra> results;

    public MitraAdapter(Context context, ArrayList<Mitra> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_relawan_child, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtnamaMitra.setText("Holland Bakery");
        holder.txtTanggal.setText("19 Jan 2019");
        Glide.with(context)
                .asBitmap()
                .load(R.drawable.food)
                .into(holder.imgMitra);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtnamaMitra, txtTanggal, txtDetail, btnRelawan, txtDaftar;
        private ImageView imgMitra;

        public ViewHolder(View itemView) {
            super(itemView);

            txtnamaMitra = itemView.findViewById(R.id.txtNamaMitra);
            txtTanggal = itemView.findViewById(R.id.txtTanggal);
            txtDetail = itemView.findViewById(R.id.txtDetail);
            btnRelawan = itemView.findViewById(R.id.btnRelawan);
            txtDaftar = itemView.findViewById(R.id.txtDaftar);
            imgMitra = itemView.findViewById(R.id.imgMitra);


        }

    }
}
