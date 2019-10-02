package com.example.foodheroes.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

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

public class RegisterActivity extends AppCompatActivity {

    EditText txtNama, txtEmail, txtNumberPhone, txtPassword, txtConfrimPass;
    DatabaseReference UserReff;
    User user;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtNama = findViewById(R.id.ednamaRelawan);
        txtEmail = findViewById(R.id.edEmail);
        txtNumberPhone = findViewById(R.id.edNumberPhone);
        txtPassword = findViewById(R.id.edPassword);
        txtConfrimPass = findViewById(R.id.edConfirmPass);
        user = new User();
        UserReff = FirebaseDatabase.getInstance().getReference().child("User");

        findViewById(R.id.btnDaftar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });
    }

    private void addUser(){
        String Nama = txtNama.getText().toString().trim();
        String Email = txtEmail.getText().toString().trim();
        String NumberPhone = txtNumberPhone.getText().toString().trim();
        String Password = txtPassword.getText().toString().trim();
        String Confirm = txtConfrimPass.getText().toString().trim();

        if(!TextUtils.isEmpty(Nama) || !TextUtils.isEmpty(Email) || !TextUtils.isEmpty(NumberPhone) || !TextUtils.isEmpty(Password)){


            Query query = FirebaseDatabase.getInstance().getReference().child("User").orderByChild("numberPhone").equalTo(txtNumberPhone.getText().toString());
            query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String numberPhoneFB = snapshot.getValue(User.class).getNumberPhone();
                    String EmailFB = snapshot.getValue(User.class).getEmail();

                    if(EmailFB.equals(Email) && numberPhoneFB.equals(txtNumberPhone)){
                        txtEmail.setError("Email Telah Digunakan");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


            user.setNama(Nama);
            user.setEmail(Email);
            user.setNumberPhone(NumberPhone);
            user.setPassword(Password);
        }

        if(Password.equals(Confirm)){
            UserReff.push().setValue(user);
            Toast.makeText(this, "Data Masuk", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        } else{
            Toast.makeText(this, "Periksa Lagi Password Anda", Toast.LENGTH_SHORT).show();
        }
    }
}
