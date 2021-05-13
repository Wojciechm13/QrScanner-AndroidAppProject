package com.example.qrscanner_appproject.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.qrscanner_appproject.data.Measurement;
import com.example.qrscanner_appproject.repository.MeasurementsRepository;
import com.example.qrscanner_appproject.repository.UserRepository;

import java.util.ArrayList;

public class MeasurementsViewModel extends AndroidViewModel {
    private final MeasurementsRepository measurementsRepository;
    private final UserRepository userRepository;


    public MeasurementsViewModel(Application application) {
        super(application);
        userRepository = UserRepository.getInstance(application);
        measurementsRepository = MeasurementsRepository.getInstance();
    }

    public void init(String key){
        measurementsRepository.init(key);
    }


    public void saveMeasurements(Measurement measurement){
        measurementsRepository.saveMeasurements(measurement);
    }


    public MutableLiveData<Measurement> getLatestMeasurements(String key){
        return MeasurementsRepository.getInstance().getLatestMeasurement(key);
    }

    //test
//    public MutableLiveData<ArrayList<Measurement>> loadMeasurements(){
//        return MeasurementsRepository.getInstance().loadAllMeasurements();
//    }
}
