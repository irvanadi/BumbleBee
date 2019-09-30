package com.example.foodheroes.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodheroes.Activities.LoginActivity;
import com.example.foodheroes.Activities.MainActivity;
import com.example.foodheroes.Models.Mitra;
import com.example.foodheroes.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class AccountFragment extends Fragment {


    DatabaseReference MitraReff;
    Mitra mitra;
    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        Objects.requireNonNull(getActivity()).setTitle("Home");

//        mitra = new Mitra();
//        MitraReff = FirebaseDatabase.getInstance().getReference().child("Mitra");
//        mitra.setNamaMitra("");
//        mitra.setAlamatMitra("Jl. Tropodo");
//        mitra.setDeskripsiMitra("Kue Enak Sehat Bergizi");
//        mitra.setKategori("Kue Basah");
//        MitraReff.push().setValue(mitra);



        view.findViewById(R.id.btnLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                FirebaseAuth.getInstance().signOut();
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        return view;
    }

}
