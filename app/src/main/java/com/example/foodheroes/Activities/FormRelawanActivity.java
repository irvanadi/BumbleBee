package com.example.foodheroes.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.foodheroes.R;

public class FormRelawanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_relawan);

        Toolbar toolbar = findViewById(R.id.topToolbarFormRelawan);
        toolbar.setTitle("Form Relawan");
        findViewById(R.id.btnDaftarRelawan2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormRelawanActivity.this, CongratsRelawanActivity.class);
                startActivity(intent);
            }
        });
    }
}
