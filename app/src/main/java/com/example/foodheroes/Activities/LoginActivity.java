package com.example.foodheroes.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

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

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    AutoCompleteTextView txtNoTelp, txtPassword;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtNoTelp = findViewById(R.id.txtNumberPhone);
        txtPassword = findViewById(R.id.txtPassword);

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NumberPhone = txtNoTelp.getText().toString();
                String pass = txtPassword.getText().toString();

                Query query = FirebaseDatabase.getInstance().getReference().child("User").orderByChild("numberPhone").equalTo(txtNoTelp.getText().toString());
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            String numberPhoneFB = snapshot.getValue(User.class).getNumberPhone();
                            String passwordFB = snapshot.getValue(User.class).getPassword();

                            if(NumberPhone.equals(numberPhoneFB) && pass.equals(passwordFB)){
                                Intent intent = new Intent(LoginActivity.this, VerificationLoginActivity.class);
                                intent.putExtra("NumberPhone", NumberPhone);
                                Toast.makeText(LoginActivity.this, "ph"+numberPhoneFB+".pass"+passwordFB, Toast.LENGTH_SHORT).show();
                                startActivity(intent);

                            } else {
                                Toast.makeText(LoginActivity.this, "No. Telpon / Password Anda Salah", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



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
