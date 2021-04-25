package com.example.qrscanner_appproject.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.qrscanner_appproject.R;

public class openAppActivity extends AppCompatActivity {

    Button logInButton;
    Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_app2);

        logInButton = findViewById(R.id.logInButton);
        signUpButton = findViewById(R.id.signUpButton);

    }
}