package com.example.qrscanner_appproject.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.qrscanner_appproject.repository.UserRepository;
import com.google.firebase.auth.FirebaseUser;

public class UserProfileViewModel extends AndroidViewModel {

    private final UserRepository userRepository;

    public UserProfileViewModel(@NonNull Application application) {
        super(application);
        userRepository = UserRepository.getInstance(application);
    }


    public LiveData<FirebaseUser> getCurrentUser(){
        return userRepository.getCurrentUser();
    }
    public void signOut() {
        userRepository.signOut();
    }
}
