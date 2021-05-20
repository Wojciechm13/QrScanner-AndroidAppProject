package com.example.qrscanner_appproject.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.qrscanner_appproject.model.Measurement;
import com.example.qrscanner_appproject.repository.MeasurementsRepository;
import com.example.qrscanner_appproject.repository.UserRepository;

import java.util.ArrayList;

public class MeasurementsViewModel extends AndroidViewModel {
    private final MeasurementsRepository measurementsRepository;
    private final UserRepository userRepository;
    private MutableLiveData<ArrayList<Measurement>> measurements;


    public MeasurementsViewModel(Application application) {
        super(application);
        userRepository = UserRepository.getInstance(application);
        measurementsRepository = MeasurementsRepository.getInstance();
    }

    public void init(String key){
        measurementsRepository.init(key);

        measurements = measurementsRepository.getAllMeasurements(key);
    }


    public void saveMeasurements(Measurement measurement){
        measurementsRepository.saveMeasurements(measurement);
    }


    public MutableLiveData<Measurement> getLatestMeasurements(String key){
        return MeasurementsRepository.getInstance().getLatestMeasurement(key);
    }


//    public MutableLiveData<ArrayList<Measurement>> loadMeasurements(String key){
//        return MeasurementsRepository.getInstance().getAllMeasurements(key);
//    }

    public MutableLiveData<ArrayList<Measurement>> loadMeasurements(){
        return measurements;
    }

//    public void eraseArrayList(){
//        measurements.removeA;
//    }
}
