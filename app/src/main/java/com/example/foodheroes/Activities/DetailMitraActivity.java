package com.example.foodheroes.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.foodheroes.Adapter.DetailMitraAdapter;
import com.example.foodheroes.Models.EventMitra;
import com.example.foodheroes.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class DetailMitraActivity extends AppCompatActivity {

    RecyclerView recDetailMitra;
    DatabaseReference EventMitraReff;
    ArrayList<EventMitra> eventMitraList;
    DetailMitraAdapter detailMitraAdapter;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mitra);


        recDetailMitra = findViewById(R.id.recDetailListMitra);
        recDetailMitra.setLayoutManager(new LinearLayoutManager(this));

        mToolbar = findViewById(R.id.topToolbarDetail);
        mToolbar.setTitle("Detail Event");

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
                    detailMitraAdapter = new DetailMitraAdapter(DetailMitraActivity.this, eventMitraList);
                    recDetailMitra.setAdapter(detailMitraAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
