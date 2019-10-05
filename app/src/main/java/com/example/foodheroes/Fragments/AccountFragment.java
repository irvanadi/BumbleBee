package com.example.foodheroes.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.foodheroes.Activities.LoginActivity;
import com.example.foodheroes.Activities.MainActivity;
import com.example.foodheroes.Models.EventMitra;
import com.example.foodheroes.Models.Mitra;
import com.example.foodheroes.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class AccountFragment extends Fragment {


    DatabaseReference MitraReff;
    Mitra mitra;
    EventMitra eventMitra;
    TextView textView;

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        Objects.requireNonNull(getActivity()).setTitle("Akun");

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


//        mitra = new Mitra();
//        MitraReff = FirebaseDatabase.getInstance().getReference().child("Mitra");
//        mitra.setNamaMitra("");
//        mitra.setAlamatMitra("Jl. Tropodo");
//        mitra.setDeskripsiMitra("Kue Enak Sehat Bergizi");
//        mitra.setKategori("Kue");
//        MitraReff.push().setValue(mitra);
//        eventMitra = new EventMitra();
//        MitraReff = FirebaseDatabase.getInstance().getReference().child("EventMitra");
//        eventMitra.setNamaMitra("Holland Bakery");
//        eventMitra.setAlamatMitra("Jl. Tropodo");
//        eventMitra.setDeskripsiMitra("Kue Enak Sehat Bergizi");
//        eventMitra.setKategori("Kue");
//        eventMitra.setTanggal(dateFormat.format(date));
//        eventMitra.setAlamatPenerima("Jl Magersari");
//        eventMitra.setRelawan("4");
//        eventMitra.setPorsi("30");
//        eventMitra.setKoor("1");
//        eventMitra.setNamaPenerima("Siti Halim");
//        Log.d("asd", MitraReff.push().getKey());

        view.findViewById(R.id.txtLogOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FirebaseAuth.getInstance().signOut();
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                firebaseAuth.signOut();

                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.txtEditAccount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Edit Nama dll
            }
        });

        view.findViewById(R.id.txtEditProfile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Edit Photo Profile
            }
        });

        return view;
    }
}
