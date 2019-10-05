package com.example.foodheroes.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.foodheroes.Adapter.MitraAdapter;
import com.example.foodheroes.Adapter.NotificationAdapter;
import com.example.foodheroes.Models.NotificationSet;
import com.example.foodheroes.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recNotif;
    ArrayList<NotificationSet> notificationSets;
    NotificationAdapter notificationAdapter;
    DatabaseReference NotificationReff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        toolbar = findViewById(R.id.topToolbar);
        toolbar.setTitle("Notification");
        recNotif = findViewById(R.id.recNotif);
        recNotif.setLayoutManager(new LinearLayoutManager(this));

        notificationSets = new ArrayList<NotificationSet>();
        SharedPreferences mSettings = NotificationActivity.this.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        String UID = mSettings.getString("UID", "UID");
        Log.d("UID123",UID);

        NotificationReff = FirebaseDatabase.getInstance().getReference().child("User").child(UID).child("Event");
        NotificationReff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                notificationSets.clear();
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        NotificationSet notificationSet = snapshot.getValue(NotificationSet.class);
//                        Log.d("notif123",notificationSet);
                        notificationSets.add(notificationSet);
                    }
                notificationAdapter = new NotificationAdapter(NotificationActivity.this, notificationSets);
                recNotif.setAdapter(notificationAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("notif123", databaseError.getMessage());
            }
        });

    }
}
