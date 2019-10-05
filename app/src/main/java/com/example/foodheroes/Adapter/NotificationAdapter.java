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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodheroes.Activities.DetailMitraActivity;
import com.example.foodheroes.Activities.FormRelawanActivity;
import com.example.foodheroes.Activities.MainActivity;
import com.example.foodheroes.Activities.MapsActivity;
import com.example.foodheroes.Models.EventMitra;
import com.example.foodheroes.Models.NotificationSet;
import com.example.foodheroes.R;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private Context context;
    private ArrayList<NotificationSet> results;

    public NotificationAdapter(Context context, ArrayList<NotificationSet> results) {
        this.context = context;
        this.results = results;
    }

    @NonNull
    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_notification, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.ViewHolder holder, int position) {
        NotificationSet result = results.get(position);

        holder.txtTanggal.setText(result.getTanggal());
        holder.txtTanggal.setText(result.getTanggal());
        holder.txtBody.setText(result.getKeterangan());

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtTanggal, txtBody, txtDetail;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            txtTanggal = itemView.findViewById(R.id.txtTanggal);
            txtBody = itemView.findViewById(R.id.txtBody);
            txtDetail = itemView.findViewById(R.id.txtDetail);
            txtDetail.setOnClickListener(new View.OnClickListener() {
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
            Intent intent = new Intent(mainActivity, FormRelawanActivity.class);
            mainActivity.startActivity(intent);
        }
    }

}
