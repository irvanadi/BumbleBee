package com.example.foodheroes.Mitra.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodheroes.Models.User;
import com.example.foodheroes.R;
import com.example.foodheroes.Relawan.Activities.LoginActivity;
import com.example.foodheroes.Relawan.Activities.MainActivity;
import com.example.foodheroes.Relawan.Activities.RegisterActivity;
import com.example.foodheroes.Relawan.Activities.VerificationLoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

public class LoginMitraActivity extends AppCompatActivity {

    EditText txtNoTelp, txtPassword;
    CountryCodePicker ccp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_mitra);

        txtNoTelp = findViewById(R.id.txtNumberPhone);
        txtPassword = findViewById(R.id.txtPassword);
        ccp = findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(txtNoTelp);

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ccp.isValidFullNumber();
                String pass = txtPassword.getText().toString();
                String NumberPhone = ccp.getFullNumberWithPlus();
                Log.d("numPhone",NumberPhone);
                Query query = FirebaseDatabase.getInstance().getReference().child("Mitra").orderByChild("numberPhone").equalTo(NumberPhone);
                query.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            String numberPhoneFB = snapshot.getValue(User.class).getNumberPhone();
                            String passwordFB = snapshot.getValue(User.class).getPassword();

                            if(NumberPhone.equals(numberPhoneFB) && pass.equals(passwordFB)){
                                Intent intent = new Intent(LoginMitraActivity.this, VerificationMitraActivity.class);
                                intent.putExtra("NumberPhone", NumberPhone);
//                                Toast.makeText(LoginActivity.this, "ph"+numberPhoneFB+".pass"+passwordFB, Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                            } else {
                                Toast.makeText(LoginMitraActivity.this, "No. Telpon / Password Anda Salah", Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(LoginMitraActivity.this, RegisterMitraActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();

//        if(FirebaseAuth.getInstance().getCurrentUser() != null){
//            Intent intent = new Intent(LoginMitraActivity.this, MainMitraActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//        }
    }

}
