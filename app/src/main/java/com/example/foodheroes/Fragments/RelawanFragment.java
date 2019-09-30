package com.example.foodheroes.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodheroes.Adapter.MitraAdapter;
import com.example.foodheroes.Models.Mitra;
import com.example.foodheroes.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;


public class RelawanFragment extends Fragment {

    ArrayList<Mitra> detailMitras;
    MitraAdapter mitraAdapter;
    DatabaseReference MitraReff;
    RecyclerView recListMitra;

    public RelawanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_relawan, container, false);

        Objects.requireNonNull(getActivity()).setTitle("Relawan");
        MitraReff = FirebaseDatabase.getInstance().getReference().child("Mitra");

        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.navRelawan);
        bottomNavigationView.setVisibility(View.VISIBLE);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        /* starts before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.WEEK_OF_MONTH, -1);

        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.WEEK_OF_MONTH, 1);

        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(view, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position){
                Toast.makeText(getContext(), dateFormat.format(date.getTime()), Toast.LENGTH_SHORT).show();
            }
        });

        recListMitra = view.findViewById(R.id.recDetailMitra);
        recListMitra.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        detailMitras = new ArrayList<Mitra>();


        MitraReff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        Mitra mitra = snapshot.getValue(Mitra.class);
                        detailMitras.add(mitra);
                        Log.d("Detail", detailMitras.toString());
                    }
                    mitraAdapter = new MitraAdapter(getContext(), detailMitras);
                    recListMitra.setAdapter(mitraAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }
}
