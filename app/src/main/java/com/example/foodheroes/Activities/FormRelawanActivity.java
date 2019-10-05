package com.example.foodheroes.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.foodheroes.Models.Mitra;
import com.example.foodheroes.Models.NotificationSet;
import com.example.foodheroes.Models.User;
import com.example.foodheroes.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class FormRelawanActivity extends AppCompatActivity {

    EditText txtUmur, txtKeterangan;
    String namaMitra, alamatMitra, jam, tanggal, position, idKey;
    DatabaseReference MitraReff;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;
    NotificationSet notificationSet;
    String NumberPhone;
    String UID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_relawan);

        Toolbar toolbar = findViewById(R.id.topToolbarFormRelawan);
        toolbar.setTitle("Form Relawan");

        Intent intent = getIntent();
        namaMitra = intent.getStringExtra("NamaMitra");
        alamatMitra = intent.getStringExtra("AlamatPenerima");
        jam = intent.getStringExtra("jam");
        tanggal = intent.getStringExtra("tanggal");
        idKey = intent.getStringExtra("idKey");
//        Log.d("Form",NumberPhone);
        Log.d("idKey", idKey);
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


//        Log.d("UID",UID);

        position = ((Spinner)findViewById(R.id.spnKategoriRelawan)).getSelectedItem().toString();
        txtUmur = findViewById(R.id.edUmur);
        txtKeterangan = findViewById(R.id.edKeterangan);

        Log.d("testing132", namaMitra+alamatMitra+jam+tanggal);

        findViewById(R.id.btnDaftarRelawan2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("testing123", position);

                Log.d("UID123", UID);
                MitraReff = FirebaseDatabase.getInstance().getReference().child("User").child(UID);
                String Umur = txtUmur.getText().toString();
                String Keterangan = txtKeterangan.getText().toString();

                notificationSet = new NotificationSet();
                notificationSet.setIdEvent(idKey);
                notificationSet.setNamaMitra(namaMitra);
                notificationSet.setAlamatMitra(alamatMitra);
                notificationSet.setPosition(position);
                notificationSet.setTanggal(tanggal);
                notificationSet.setJam(jam);
                notificationSet.setUmur(Umur);
                notificationSet.setKeterangan(Keterangan);
                MitraReff.push().setValue(notificationSet);


//                Intent intent = new Intent(FormRelawanActivity.this, CongratsRelawanActivity.class);
//                startActivity(intent);
            }
        });
    }
}
