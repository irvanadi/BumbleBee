package com.example.foodheroes.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.foodheroes.Fragments.AccountFragment;
import com.example.foodheroes.Fragments.HomeFragment;
import com.example.foodheroes.Fragments.PenerimaFragment;
import com.example.foodheroes.R;
import com.example.foodheroes.Fragments.RelawanFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = findViewById(R.id.topToolbar);
        setSupportActionBar(mToolbar);
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
