package com.example.foodheroes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    AutoCompleteTextView txtNoTelp, txtPassword;
    Button btnLogin;
    FirebaseAuth mAuth;
    FirebaseUser UID;
    DatabaseReference UserReff;
    FirebaseDatabase mfirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtNoTelp = findViewById(R.id.txtNumberPhone);
        txtPassword = findViewById(R.id.txtPassword);
        UserReff = FirebaseDatabase.getInstance().getReference("User");

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NumberPhone = txtNoTelp.getText().toString();
                String pass = txtPassword.getText().toString();
                Intent intent = new Intent(LoginActivity.this, VerificationLogin.class);
                intent.putExtra("NumberPhone", NumberPhone);
                intent.putExtra("Password", pass);
                startActivity(intent);
            }
        });

        findViewById(R.id.txtToRegist2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();

        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

}
