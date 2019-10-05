package com.example.foodheroes.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodheroes.Activities.DetailMitraActivity;
import com.example.foodheroes.Activities.FormRelawanActivity;
import com.example.foodheroes.Activities.MapsActivity;
import com.example.foodheroes.Activities.MapsRelawanActivity;
import com.example.foodheroes.Models.EventMitra;
import com.example.foodheroes.Models.Mitra;
import com.example.foodheroes.R;

import java.util.ArrayList;

public class DetailMitraAdapter extends RecyclerView.Adapter<DetailMitraAdapter.ViewHolder> {

    private Context context;
    private ArrayList<EventMitra> results;
    String NamaMitra, AlamatPenerima, AlamatMitra, jam, tanggal, idKey, NumberPhone;

    public DetailMitraAdapter(Context context, ArrayList<EventMitra> results, String idKey) {
        this.context = context;
        this.results = results;
        this.idKey = idKey;
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
        AlamatPenerima = result.getAlamatPenerima();
        holder.btnRelawan.setText(" 3 Relawan");
        holder.txtSpecificPorsi.setText(result.getPorsi() + " Porsi");
        holder.txtSpecificKategori.setText(result.getKategori());
        NamaMitra = result.getNamaMitra();
        AlamatMitra = result.getAlamatMitra();
        AlamatPenerima = result.getAlamatPenerima();
        tanggal = result.getTanggal();
        jam = result.getJam();
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtChildNamaMitra, txtAlamatMitra, txtDescriptionChild, txtSpecificTanggal, txtSpecificKategori, txtSpecificPorsi, txtSpecificAlamat;
        private Button btnRelawan, btnDaftarRelawan, btnMaps;
        private ImageView imgMitra;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            imgMitra = itemView.findViewById(R.id.imgChildRelawan);
            txtChildNamaMitra = itemView.findViewById(R.id.txtChildNamaMitra);
            txtAlamatMitra = itemView.findViewById(R.id.txtAlamatMitra);
            txtDescriptionChild = itemView.findViewById(R.id.txtDescriptionChild);
            txtSpecificTanggal = itemView.findViewById(R.id.txtSpecificTanggal);
            txtSpecificKategori = itemView.findViewById(R.id.txtSpecificKategori);
            txtSpecificPorsi = itemView.findViewById(R.id.txtSpecificPorsi);
            txtSpecificAlamat = itemView.findViewById(R.id.txtSpecificAlamat);
            btnRelawan = itemView.findViewById(R.id.btnRelawan);
            btnDaftarRelawan = itemView.findViewById(R.id.btnDaftarRelawan);
            btnDaftarRelawan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                toFormRelawan();
                }
            });
            itemView.findViewById(R.id.btnMaps).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toMaps();
                }
            });
        }

        @Override
        public void onClick(View v) {

        }
        public void toFormRelawan(){
            DetailMitraActivity detailMitraActivity = (DetailMitraActivity) itemView.getContext();

            Log.d("NumPhone", "Main"+NumberPhone);

            Intent intent1 = new Intent(detailMitraActivity, FormRelawanActivity.class);
            intent1.putExtra("NamaMitra",NamaMitra);
            intent1.putExtra("AlamatPenerima",AlamatPenerima);
            intent1.putExtra("jam",jam);
            intent1.putExtra("tanggal",tanggal);
            intent1.putExtra("idKey",idKey);
            intent1.putExtra("DetailMtraAdapter",NumberPhone);
            detailMitraActivity.startActivity(intent1);

        }
        private void toMaps(){
            DetailMitraActivity detailMitraActivity = (DetailMitraActivity) itemView.getContext();

            Intent intent1 = new Intent(detailMitraActivity, MapsActivity.class);
            intent1.putExtra("AlamatPenerima",AlamatPenerima);
            intent1.putExtra("DetailMtraAdapter",NumberPhone);
//            Toast.makeText(detailMitraActivity, AlamatPenerima, Toast.LENGTH_SHORT).show();
            detailMitraActivity.startActivity(intent1);
        }
    }
}
