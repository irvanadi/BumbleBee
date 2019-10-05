package com.example.foodheroes.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.foodheroes.Fragments.AccountFragment;
import com.example.foodheroes.Fragments.HomeFragment;
import com.example.foodheroes.Fragments.PenerimaFragment;
import com.example.foodheroes.R;
import com.example.foodheroes.Fragments.RelawanFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    Toolbar mToolbar;
    String NumberPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = findViewById(R.id.topToolbar);
        Intent intent = getIntent();
        NumberPhone = intent.getStringExtra("NumberPhone");
//        Log.d("NumPhone", NumberPhone);
        setSupportActionBar(mToolbar);
        BottomNavigationView navigationView = findViewById(R.id.navRelawan);
        navigationView.setOnNavigationItemSelectedListener(this);

        loadFragment(new HomeFragment());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_notification, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.notif:
                Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
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
