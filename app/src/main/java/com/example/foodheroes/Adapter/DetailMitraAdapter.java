package com.example.foodheroes.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodheroes.Models.EventMitra;
import com.example.foodheroes.Models.Mitra;
import com.example.foodheroes.R;

import java.util.ArrayList;

public class DetailMitraAdapter extends RecyclerView.Adapter<DetailMitraAdapter.ViewHolder> {

    private Context context;
    private ArrayList<EventMitra> results;

    public DetailMitraAdapter(Context context, ArrayList<EventMitra> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_detail_relawan, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EventMitra result = results.get(position);

        Glide.with(context)
                .asBitmap()
                .load(R.drawable.food)
                .into(holder.imgMitra);

        holder.txtChildNamaMitra.setText(result.getNamaMitra());
        holder.txtAlamatMitra.setText(result.getAlamatMitra());
        holder.txtDescriptionChild.setText(result.getDeskripsiMitra());
        holder.txtSpecificTanggal.setText(result.getTanggal());
        holder.txtSpecificAlamat.setText(result.getAlamatPenerima());
        holder.btnRelawan.setText(result.getRelawan() + " Relawan");
        holder.txtSpecificPorsi.setText(result.getPorsi() + " Porsi");
        holder.txtSpecificKategori.setText(result.getKategori());
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtChildNamaMitra, txtAlamatMitra, txtDescriptionChild, txtSpecificTanggal, txtSpecificKategori, txtSpecificPorsi, txtSpecificAlamat;
        private Button btnRelawan;
        private ImageView imgMitra;

        public ViewHolder(View itemView) {
            super(itemView);

            imgMitra = itemView.findViewById(R.id.imgChildRelawan);
            txtChildNamaMitra = itemView.findViewById(R.id.txtChildNamaMitra);
            txtAlamatMitra = itemView.findViewById(R.id.txtAlamatMitra);
            txtDescriptionChild = itemView.findViewById(R.id.txtDescriptionChild);
            txtSpecificTanggal = itemView.findViewById(R.id.txtSpecificTanggal);
            txtSpecificKategori = itemView.findViewById(R.id.txtSpecificKategori);
            txtSpecificPorsi = itemView.findViewById(R.id.txtSpecificPorsi);
            txtSpecificAlamat = itemView.findViewById(R.id.txtSpecificAlamat);
            btnRelawan = itemView.findViewById(R.id.btnRelawan);
        }
    }
}
