package com.example.foodheroes.Relawan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodheroes.Relawan.Activities.MainActivity;
import com.example.foodheroes.Relawan.Activities.MapsActivity;
import com.example.foodheroes.Models.EventMitra2;
import com.example.foodheroes.R;

import java.util.ArrayList;

public class PenerimaAdapter extends RecyclerView.Adapter<PenerimaAdapter.ViewHolder>{

    private Context context;
    private ArrayList<EventMitra2> results;
    String alamatMitra;

    public PenerimaAdapter(Context context, ArrayList<EventMitra2> results) {
        this.context = context;
        this.results = results;
    }

    @NonNull
    @Override
    public PenerimaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_penerima, parent, false);
        return new PenerimaAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EventMitra2 eventMitra = results.get(position);
        holder.txtAlamat.setText(eventMitra.getAlamatPenerima());
        holder.txtCaption.setText("Terdapat banyak masyarakat jalanan yang ...");
        Glide.with(context)
                .asBitmap()
                .load(R.drawable.google_maps)
                .into(holder.imageViewMap);
        alamatMitra = eventMitra.getAlamatPenerima();
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtAlamat, txtCaption;
        ImageView imageViewMap;
        ConstraintLayout toMaps;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            txtAlamat = itemView.findViewById(R.id.title);
            txtCaption = itemView.findViewById(R.id.caption);
            imageViewMap = itemView.findViewById(R.id.imgmap);
            toMaps = itemView.findViewById(R.id.toMaps);
            toMaps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toForm();
                }
            });

        }

        @Override
        public void onClick(View v) {

        }

        public void toForm(){
            MainActivity mainActivity = (MainActivity) itemView.getContext();
            Intent intent = new Intent(mainActivity, MapsActivity.class);
            intent.putExtra("AlamatPenerima",alamatMitra);
            mainActivity.startActivity(intent);
        }
    }

}
