package com.example.qrscanner_appproject.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.qrscanner_appproject.data.Patient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PatientsRepository {

    private static PatientsRepository instance;
    private ArrayList<Patient> patients = new ArrayList<>();
    private MutableLiveData<ArrayList<Patient>> patient = new MutableLiveData<>();



    public static synchronized PatientsRepository getInstance(){

        if (instance == null){
            instance = new PatientsRepository();
        }

        return instance;
    }

    public MutableLiveData<ArrayList<Patient>> getPatients(){
        if (patients.size() == 0){
            loadPatients();
        }


        patient.setValue(patients);

        return patient;
    }

    private void loadPatients() {
        DatabaseReference ref = FirebaseDatabase.getInstance("https://qrscannerandroidapp-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Patients");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    patients.add(snapshot.getValue(Patient.class));
               }
               patient.postValue(patients);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
