package com.example.qrscanner_appproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.qrscanner_appproject.view.MainFragment;
import com.example.qrscanner_appproject.view.PatientsFragment;
import com.example.qrscanner_appproject.view.QrScannerFragment;
import com.example.qrscanner_appproject.view.UserProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bottom navigation
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        //Which item in bottom_nav should be checked by default
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);

        }

        //todo initialize the search in the topbar functionality (has some error)
        //Init of topMenu
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.top_menu, menu);
//        MenuItem item = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) item.getActionView();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                recyclerAdapter.getFilter().filter(newText);
//                return false;
//            }
//        });
//        return super.onCreateOptionsMenu(menu);
//    }

    QrScannerFragment qrScannerFragment = new QrScannerFragment();
        PatientsFragment patientsFragment = new PatientsFragment();
        UserProfileFragment userProfileFragment = new UserProfileFragment();
        MainFragment mainFragment = new MainFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.navigation_home:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.fragmentContainer, mainFragment).commit();
                return true;

            case R.id.navigation_scanner:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.fragmentContainer, qrScannerFragment).commit();
                return true;

            case R.id.navigation_patients:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.fragmentContainer, patientsFragment).commit();
                return true;

            case R.id.navigation_profile:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.fragmentContainer, userProfileFragment).commit();
                return true;
        }
        return false;
    }
}


