package com.example.foodheroes.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.ConfigurationCompat;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.load.engine.Resource;
import com.example.foodheroes.Models.User;
import com.example.foodheroes.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

import java.util.Locale;
import java.util.ResourceBundle;

public class RegisterActivity extends AppCompatActivity {

    EditText txtNama, txtEmail, txtNumberPhone, txtPassword, txtConfrimPass;
    DatabaseReference UserReff;
    User user;
    FirebaseAuth mAuth;
    Locale locale;
    CountryCodePicker ccp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtNama = findViewById(R.id.ednamaRelawan);
        txtEmail = findViewById(R.id.edEmail);
        txtNumberPhone = findViewById(R.id.edNumberPhone);
        txtPassword = findViewById(R.id.edPassword);
        txtConfrimPass = findViewById(R.id.edConfirmPass);

        ccp = findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(txtNumberPhone);
        user = new User();
        UserReff = FirebaseDatabase.getInstance().getReference().child("User");
        locale = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration()).get(0);


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

        Log.d("masuk","masuk");
            Query query = FirebaseDatabase.getInstance().getReference().child("User").orderByChild("numberPhone").equalTo(NumberPhone);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String numberPhoneFB = snapshot.getValue(User.class).getNumberPhone();
                        String EmailFB = snapshot.getValue(User.class).getEmail();
                        Toast.makeText(RegisterActivity.this, "masuk 1", Toast.LENGTH_SHORT).show();
                        Log.d("masuk", "masuk1");
                        if (EmailFB.equals(Email) && numberPhoneFB.equals(NumberPhone)) {
                            Toast.makeText(RegisterActivity.this, "gagal1", Toast.LENGTH_SHORT).show();
                            Log.d("gagal", "gagal1");
                            txtEmail.setError("Email / numberPhone Telah Digunakan");
                        }
                    }
                }
                else{
                        if(Password.equals(Confirm)){
                            Toast.makeText(RegisterActivity.this, "masuk2", Toast.LENGTH_SHORT).show();
                            Log.d("masuk","masuk2");
                            user.setNama(Nama);
                            user.setEmail(Email);
                            user.setNumberPhone(NumberPhone);
                            user.setPassword(Password);
                            UserReff.push().setValue(user);
                            Toast.makeText(RegisterActivity.this, "Data Masuk", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, VerificationLoginActivity.class);
                            intent.putExtra("NumberPhone", NumberPhone);
                            startActivity(intent);
                        } else{
                            Toast.makeText(RegisterActivity.this, "Periksa Lagi Password Anda", Toast.LENGTH_SHORT).show();
                        }
                    }
                }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}