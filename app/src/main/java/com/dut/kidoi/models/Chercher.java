package com.dut.kidoi.models;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.dut.kidoi.Demander;
import com.dut.kidoi.Profil;
import com.dut.kidoi.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Chercher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chercher);

        BottomNavigationView nav = findViewById(R.id.bottom_nav);
        nav.setOnNavigationItemSelectedListener(navListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    //Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.action_demander:
                            startActivity(new Intent(Chercher.this, Demander.class));
                            break;
                        case R.id.action_envoyer:
                            startActivity(new Intent(Chercher.this, Envoyer.class));
                            break;
                        case R.id.action_chercher:
                            startActivity(new Intent(Chercher.this, Chercher.class));
                            break;
                        case R.id.action_profil:
                            startActivity(new Intent(Chercher.this,Profil.class));
                            break;
                    }
                    //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };
}
