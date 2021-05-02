package com.example.qrscanner_appproject.viewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.qrscanner_appproject.data.Patient;
import com.example.qrscanner_appproject.repository.PatientsRepository;

import java.util.ArrayList;

public class PatientsViewModel extends ViewModel {

    MutableLiveData<ArrayList<Patient>> patients;

    public void init(){
        if (patients != null){
            return;
        }

        patients = PatientsRepository.getInstance().getPatients();
    }

    public LiveData<ArrayList<Patient>> getPatientsLiveData(){
        return patients;
    }
}
