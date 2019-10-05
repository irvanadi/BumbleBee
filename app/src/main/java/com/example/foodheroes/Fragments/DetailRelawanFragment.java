package com.example.foodheroes.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.example.foodheroes.Adapter.DetailMitraAdapter;
import com.example.foodheroes.Adapter.MitraAdapter;
import com.example.foodheroes.Models.EventMitra;
import com.example.foodheroes.Models.Mitra;
import com.example.foodheroes.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class DetailRelawanFragment extends Fragment {

    DatabaseReference EventMitraReff;
    ArrayList<EventMitra> eventMitraList;
    RecyclerView recDetailMitra;
    DetailMitraAdapter detailMitraAdapter;
    String asd;

    public DetailRelawanFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_relawan, container, false);

        Objects.requireNonNull(getActivity()).setTitle("Detail");

        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.navRelawan);
        bottomNavigationView.setVisibility(View.GONE);


        recDetailMitra = view.findViewById(R.id.recDetailListMitra);
        recDetailMitra.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        eventMitraList = new ArrayList<EventMitra>();
        EventMitraReff = FirebaseDatabase.getInstance().getReference().child("EventMitra");

        EventMitraReff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        EventMitra eventMitra = snapshot.getValue(EventMitra.class);
                        eventMitraList.add(eventMitra);
                        Log.d("Detail1", eventMitra.toString());
                    }
                    detailMitraAdapter = new DetailMitraAdapter(getContext(), eventMitraList, asd);
                    recDetailMitra.setAdapter(detailMitraAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }

}
