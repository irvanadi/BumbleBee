package com.example.foodheroes.Relawan.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.foodheroes.Relawan.Fragments.AccountFragment;
import com.example.foodheroes.Relawan.Fragments.HomeFragment;
import com.example.foodheroes.Relawan.Fragments.PenerimaFragment;
import com.example.foodheroes.R;
import com.example.foodheroes.Relawan.Fragments.RelawanFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    Toolbar mToolbar;
    String NumberPhone, UID;
    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = findViewById(R.id.topToolbar);
        Intent intent = getIntent();
        NumberPhone = intent.getStringExtra("NumberPhone");
//        Log.d("NumPhone", NumberPhone);
        setSupportActionBar(mToolbar);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        NumberPhone = firebaseUser.getPhoneNumber();
        Log.d("CurrentID",firebaseUser.getPhoneNumber());

        Query query = FirebaseDatabase.getInstance().getReference().child("User").orderByChild("numberPhone").equalTo(NumberPhone);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    UID = snapshot.getKey();
                    Log.d("CurrencID", UID);
                    SharedPreferences mSettings = MainActivity.this.getSharedPreferences("Settings", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = mSettings.edit();
                    editor.putString("UID",UID);
                    editor.apply();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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
