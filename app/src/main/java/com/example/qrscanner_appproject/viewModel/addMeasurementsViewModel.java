package com.example.qrscanner_appproject.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.qrscanner_appproject.data.Measurement;
import com.example.qrscanner_appproject.repository.MeasurementsRepository;
import com.example.qrscanner_appproject.repository.UserRepository;

public class addMeasurementsViewModel extends AndroidViewModel {
    private final MeasurementsRepository measurementsRepository;
    private final UserRepository userRepository;


    public addMeasurementsViewModel(Application application) {
        super(application);
        userRepository = UserRepository.getInstance(application);
        measurementsRepository = MeasurementsRepository.getInstance();
    }

    public void init(){
        String userId = userRepository.getCurrentUser().getValue().getUid();
        measurementsRepository.init(userId);
    }


    public void saveMeasurements(Measurement measurement){
        measurementsRepository.saveMeasurements(measurement);
    }
}
