package com.example.foodheroes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    AutoCompleteTextView txtEmail, txtNumberPhone, txtPassword, txtConfrimPass;
    DatabaseReference UserReff;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txtEmail = findViewById(R.id.txtEmail);
        txtNumberPhone = findViewById(R.id.txtNumberPhone);
        txtPassword = findViewById(R.id.txtPassword);
        txtConfrimPass = findViewById(R.id.txtRePassword);
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
        String Email = txtEmail.getText().toString().trim();
        String NumberPhone = txtNumberPhone.getText().toString().trim();
        String Password = txtPassword.getText().toString().trim();
        String Confirm = txtConfrimPass.getText().toString().trim();

        if(!TextUtils.isEmpty(Email) || !TextUtils.isEmpty(NumberPhone) || !TextUtils.isEmpty(Password)){
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
