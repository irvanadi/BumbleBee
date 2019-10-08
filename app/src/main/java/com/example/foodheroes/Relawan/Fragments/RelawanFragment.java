package com.example.foodheroes.Relawan.Fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodheroes.Relawan.Activities.VerificationLoginActivity;
import com.example.foodheroes.Relawan.Adapter.MitraAdapter;
import com.example.foodheroes.Models.EventMitra;
import com.example.foodheroes.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
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

    ArrayList<EventMitra> detailMitras;
    MitraAdapter mitraAdapter;
    DatabaseReference MitraReff;
    RecyclerView recListMitra;
    TextView txtDataKosong;
    ImageView imgSeparator;
    String idKey;

    public RelawanFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_relawan, container, false);

        final ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage("Loading ...");
        dialog.setCancelable(false);
        dialog.show();

        imgSeparator = view.findViewById(R.id.imgSeparator);
        txtDataKosong = view.findViewById(R.id.txtDataKosong);
        recListMitra = view.findViewById(R.id.recDetailMitra);
        recListMitra.setLayoutManager(new LinearLayoutManager(getContext()));
        txtDataKosong.setVisibility(View.INVISIBLE);


        Objects.requireNonNull(getActivity()).setTitle("Relawan");

        MitraReff = FirebaseDatabase.getInstance().getReference().child("Mitra");

        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.navRelawan);
        bottomNavigationView.setVisibility(View.VISIBLE);

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dateFormat1 = new SimpleDateFormat("hh:mm");
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

        detailMitras = new ArrayList<EventMitra>();

        Query query = FirebaseDatabase.getInstance().getReference().child("EventMitra").orderByChild("tanggal").equalTo(dateFormat.format(date.getTime()));
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    txtDataKosong.setVisibility(View.INVISIBLE);
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        String tanggal = snapshot.getValue(EventMitra.class).getTanggal();
                        Log.d("Tanggal",tanggal);

                        if(tanggal.equals(dateFormat.format(date.getTime()))){
                            Intent intent = new Intent(getContext(), VerificationLoginActivity.class);
//                            Toast.makeText(getContext(), tanggal, Toast.LENGTH_SHORT).show();
                            EventMitra eventMitra = snapshot.getValue(EventMitra.class);
                            detailMitras.add(eventMitra);
                            dialog.dismiss();
//                                Toast.makeText(getContext(), "ph"+numberPhoneFB+".pass"+passwordFB, Toast.LENGTH_SHORT).show();
//                                startActivity(intent);

                        } else {
                            dialog.dismiss();
                            Toast.makeText(getContext(), "Maaf hari ini tidak ada Mitra yang akan ber-Donasi", Toast.LENGTH_LONG).show();
                        }

                    }
                    recListMitra.setVisibility(View.VISIBLE);

                } else {
                    dialog.dismiss();
                    recListMitra.setVisibility(View.INVISIBLE);
                    txtDataKosong.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(), "Maaf hari ini tidak ada Mitra yang akan ber-Donasi", Toast.LENGTH_SHORT).show();
                }
                mitraAdapter = new MitraAdapter(getContext(), detailMitras);
                recListMitra.setAdapter(mitraAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position){
                detailMitras.clear();
//                Toast.makeText(getContext(), dateFormat.format(date.getTime()), Toast.LENGTH_SHORT).show();
                Query query = FirebaseDatabase.getInstance().getReference().child("EventMitra").orderByChild("tanggal").equalTo(dateFormat.format(date.getTime()));
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            txtDataKosong.setVisibility(View.INVISIBLE);
                            for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                                String tanggal = snapshot.getValue(EventMitra.class).getTanggal();
                                Log.d("Tanggal123",tanggal);
                                if(tanggal.equals(dateFormat.format(date.getTime()))){
//                                    Intent intent = new Intent(getContext(), VerificationLoginActivity.class);
//                        Toast.makeText(getContext(), tanggal, Toast.LENGTH_SHORT).show();
                                    EventMitra eventMitra = snapshot.getValue(EventMitra.class);
                                    detailMitras.add(eventMitra);
//                        dialog.dismiss();
//                                Toast.makeText(getContext(), "ph"+numberPhoneFB+".pass"+passwordFB, Toast.LENGTH_SHORT).show();
//                                startActivity(intent);

                                } else {

//                                    Toast.makeText(getContext(), "No. Telpon / Password Anda Salah", Toast.LENGTH_SHORT).show();
                                }
                            }

                            recListMitra.setVisibility(View.VISIBLE);
                        } else {
                            recListMitra.setVisibility(View.INVISIBLE);
                            txtDataKosong.setVisibility(View.VISIBLE);
                            Toast.makeText(getContext(), "Maaf hari ini tidak ada Mitra yang akan ber-Donasi", Toast.LENGTH_SHORT).show();
                        }

                        mitraAdapter = new MitraAdapter(getContext(), detailMitras);
                        recListMitra.setAdapter(mitraAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        return view;
    }
}
