package com.example.foodheroes.Relawan.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodheroes.Relawan.Adapter.PenerimaAdapter;
import com.example.foodheroes.Models.EventMitra2;
import com.example.foodheroes.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class PenerimaFragment extends Fragment {

    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<EventMitra2> eventMitraList;
    PenerimaAdapter penerimaAdapter;

    public PenerimaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Objects.requireNonNull(getActivity()).setTitle("Penerima");

        View view = inflater.inflate(R.layout.fragment_penerima, container, false);

        recyclerView = view.findViewById(R.id.recPenerima);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        eventMitraList = new ArrayList<EventMitra2>();
        reference = FirebaseDatabase.getInstance().getReference().child("EventMitra");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    EventMitra2 eventMitra = snapshot.getValue(EventMitra2.class);
                    eventMitraList.add(eventMitra);
                }
                penerimaAdapter = new PenerimaAdapter(getContext(), eventMitraList);
                recyclerView.setAdapter(penerimaAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }
}
