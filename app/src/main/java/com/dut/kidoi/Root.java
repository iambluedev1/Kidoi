package com.dut.kidoi;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.dut.kidoi.ui.chercher.ChercherFragment;
import com.dut.kidoi.ui.demander.DemanderFragment;
import com.dut.kidoi.ui.envoyer.EnvoyerFragment;
import com.dut.kidoi.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Root extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    openFragment(HomeFragment.newInstance());
                    return true;
                case R.id.navigation_demander:
                    openFragment(DemanderFragment.newInstance());
                    return true;
                case R.id.navigation_envoyer:
                    openFragment(EnvoyerFragment.newInstance());
                    return true;
                case R.id.navigation_chercher:
                    openFragment(ChercherFragment.newInstance());
                    return true;
            }
            return false;
        });
        openFragment(HomeFragment.newInstance());

       /* FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = new HomeFragment();
        fragmentTransaction.add(R.id.nav_host_fragment, fragment);
        fragmentTransaction.commit();

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();

        NavigationUI.setupWithNavController(navView, navController);
        System.out.println(appBarConfiguration);*/
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
