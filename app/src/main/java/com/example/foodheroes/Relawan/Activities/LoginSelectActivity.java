package com.example.foodheroes.Relawan.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.foodheroes.Mitra.Activities.LoginMitraActivity;
import com.example.foodheroes.R;

public class LoginSelectActivity extends AppCompatActivity {

    CardView cardViewleft, cardViewright;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_select);
        findViewById(R.id.cardleft).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginSelectActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.cardright).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginSelectActivity.this, LoginMitraActivity.class);
                startActivity(intent);
            }
        });
    }

}
