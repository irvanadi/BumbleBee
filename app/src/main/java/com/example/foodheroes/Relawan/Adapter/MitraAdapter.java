package com.example.foodheroes.Relawan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodheroes.Relawan.Activities.DetailMitraActivity;
import com.example.foodheroes.Relawan.Activities.FormRelawanActivity;
import com.example.foodheroes.Relawan.Activities.MainActivity;
import com.example.foodheroes.Models.EventMitra;
import com.example.foodheroes.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MitraAdapter extends RecyclerView.Adapter<MitraAdapter.ViewHolder> {

    private Context context;
    private ArrayList<EventMitra> results;
    String NumberPhone, UID, NamaMitra, jam, tanggal;
    FirebaseUser firebaseUser;

    public MitraAdapter(Context context, ArrayList<EventMitra> results) {
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
        EventMitra result = results.get(position);

        holder.txtnamaMitra.setText(result.getNamaMitra());
        holder.txtTanggal.setText(result.getTanggal());

        NamaMitra = result.getNamaMitra();
        jam = result.getJam();
        tanggal = result.getTanggal();


                Glide.with(context)
                .asBitmap()
                .load(R.drawable.food)
                .into(holder.imgMitra);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtnamaMitra, txtTanggal, txtDetail, txtDaftar, txtJam;
        Button btnKoor, btnRelawan;
        private ImageView imgMitra;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);


            txtnamaMitra = itemView.findViewById(R.id.txtNamaMitra);
            txtTanggal = itemView.findViewById(R.id.txtTanggal);
            txtDetail = itemView.findViewById(R.id.txtDetail);
            btnRelawan = itemView.findViewById(R.id.btnRelawan);
            btnKoor = itemView.findViewById(R.id.btnKoor);
            txtDaftar = itemView.findViewById(R.id.txtDaftar);
            imgMitra = itemView.findViewById(R.id.imgMitra);
            txtJam = itemView.findViewById(R.id.txtJam);

            txtDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toDetails();
                }
            });

            txtDaftar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toDaftar();
                }
            });

        }

        private void toDetails(){
            MainActivity mainActivity = (MainActivity) itemView.getContext();

            Intent intent = new Intent(mainActivity, DetailMitraActivity.class);
            intent.putExtra("NumberPhone",NumberPhone);
            Log.d("NumPhone", "MitraAdapter"+NumberPhone);
            mainActivity.startActivity(intent);

//            DetailRelawanFragment nextFrag = new DetailRelawanFragment();
//            RelawanFragment thisFrag = new RelawanFragment();
//            mainActivity.getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.fragmentContainerNoNavBar, nextFrag, "DetailRelawanFragment")
//                    .hide(thisFrag)
//                    .addToBackStack(null)
//                    .commit();
        }

        @Override
        public void onClick(View v) {

        }
        private void toDaftar(){
            MainActivity mainActivity = (MainActivity) itemView.getContext();

            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            NumberPhone = firebaseUser.getPhoneNumber();
            Log.d("CurrentID",firebaseUser.getPhoneNumber());

            Query query = FirebaseDatabase.getInstance().getReference().child("User").orderByChild("numberPhone").equalTo(NumberPhone);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        UID = snapshot.getKey();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            Intent intent = new Intent(mainActivity, FormRelawanActivity.class);
            intent.putExtra("NumberPhone",NumberPhone);
            intent.putExtra("NamaMitra",NamaMitra);
            intent.putExtra("jam",jam);
            intent.putExtra("tanggal",tanggal);
            Log.d("NumPhone", "MitraAdapter"+NumberPhone);
            mainActivity.startActivity(intent);

        }
    }
}
