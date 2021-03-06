package com.example.qrscanner_appproject.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qrscanner_appproject.R;
import com.example.qrscanner_appproject.view.fragment.MainFragment;
import com.example.qrscanner_appproject.view.fragment.PatientsFragment;
import com.example.qrscanner_appproject.view.fragment.QrScannerFragment;
import com.example.qrscanner_appproject.view.fragment.UserProfileFragment;
import com.example.qrscanner_appproject.viewModel.MainActivityViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bottomNavigationView;
    private MainActivityViewModel viewModel;
    boolean toastDisplayed = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        viewModel.init();
        checkIfSignedIn();
        setContentView(R.layout.activity_main);


        //Bottom navigation
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        //Which item in bottom_nav should be checked by default
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);

        }



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

    private void checkIfSignedIn() {
        viewModel.getCurrentUser().observe(this, user -> {
            if (user != null) {
                if (!toastDisplayed) {
                    String message = "Welcome " + user.getDisplayName();
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT ).show();
                    toastDisplayed = true;
                }

            } else
                startLoginActivity();
        });
    }

    private void startLoginActivity() {
        startActivity(new Intent(this, SignInActivity.class));
        finish();
    }


}


