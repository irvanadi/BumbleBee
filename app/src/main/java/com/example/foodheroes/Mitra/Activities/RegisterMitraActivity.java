package com.example.foodheroes.Mitra.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.ConfigurationCompat;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodheroes.Models.Mitra;
import com.example.foodheroes.Models.User;
import com.example.foodheroes.R;
import com.example.foodheroes.Relawan.Activities.RegisterActivity;
import com.example.foodheroes.Relawan.Activities.VerificationLoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

import java.util.Locale;

public class RegisterMitraActivity extends AppCompatActivity {

    EditText txtNama, txtEmail, txtNumberPhone, txtPassword, txtConfrimPass, txtAlamat;
    DatabaseReference MitraReff;
    User user;
    FirebaseAuth mAuth;
    CountryCodePicker ccp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_mitra);
        txtNama = findViewById(R.id.edNamaMitra);
        txtEmail = findViewById(R.id.edEmail);
        txtNumberPhone = findViewById(R.id.edNumberPhone);
        txtPassword = findViewById(R.id.edPassword);
        txtConfrimPass = findViewById(R.id.edConfirmPass);
        txtAlamat = findViewById(R.id.edAlamat);

        ccp = findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(txtNumberPhone);
        user = new User();
        MitraReff = FirebaseDatabase.getInstance().getReference().child("Mitra");

        findViewById(R.id.btnDaftar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });
    }

    private void addUser(){

        ccp.isValidFullNumber();
        String Nama = txtNama.getText().toString().trim();
        String Email = txtEmail.getText().toString().trim();
        String NumberPhone = ccp.getFullNumberWithPlus();
        String Password = txtPassword.getText().toString().trim();
        String Confirm = txtConfrimPass.getText().toString().trim();
        String Alamat = txtAlamat.getText().toString().trim();

        Log.d("masuk","masuk");
        Query query = FirebaseDatabase.getInstance().getReference().child("Mitra").orderByChild("numberPhone").equalTo(NumberPhone);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String numberPhoneFB = snapshot.getValue(User.class).getNumberPhone();
                        String EmailFB = snapshot.getValue(User.class).getEmail();
                        Toast.makeText(RegisterMitraActivity.this, "masuk 1", Toast.LENGTH_SHORT).show();
                        Log.d("masuk", "masuk1");
                        if (EmailFB.equals(Email) || numberPhoneFB.equals(NumberPhone)) {
                            Toast.makeText(RegisterMitraActivity.this, "gagal1", Toast.LENGTH_SHORT).show();
                            Log.d("gagal", "gagal1");
                        }
                    }
                }else{
                    if(Password.equals(Confirm)){
                        Toast.makeText(RegisterMitraActivity.this, "masuk2", Toast.LENGTH_SHORT).show();
                        Log.d("masuk","masuk2");
//                        user.setNama(Nama);
//                        user.setEmail(Email);
//                        user.setNumberPhone(NumberPhone);
//                        user.setPassword(Password);
//                        MitraReff.push().setValue(user);
//                            Toast.makeText(RegisterActivity.this, "Data Masuk", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterMitraActivity.this, VerificationMitraActivity.class);
                        intent.putExtra("NumberPhone", NumberPhone);
                        intent.putExtra("Nama", Nama);
                        intent.putExtra("Email", Email);
                        intent.putExtra("NumberPhone",NumberPhone);
                        intent.putExtra("Alamat",Alamat);
                        intent.putExtra("Password",Password);
                        Log.d("CheckLog1", NumberPhone+Nama+Email+Password+Alamat);
                        startActivity(intent);
                    } else{
                        Toast.makeText(RegisterMitraActivity.this, "Periksa Lagi Password Anda", Toast.LENGTH_SHORT).show();
                    }
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
