package com.example.foodheroes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigationView = findViewById(R.id.navRelawan);
        navigationView.setOnNavigationItemSelectedListener(this);
        loadFragment(new HomeFragment());

    }

    private boolean loadFragment(Fragment fragment){
        if(fragment != null){

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .commit();
            return true;
        }

        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment = null;

        switch (menuItem.getItemId()){

            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;

            case R.id.navigation_relawan:
                fragment = new RelawanFragment();
                break;

            case R.id.navigation_penerima:
                fragment = new PenerimaFragment();
                break;

            case R.id.navigation_akun:
                fragment = new AccountFragment();
                break;
        }

        return loadFragment(fragment);
    }
}
