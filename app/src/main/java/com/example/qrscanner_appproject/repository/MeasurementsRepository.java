package com.example.qrscanner_appproject.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.qrscanner_appproject.data.Measurement;
import com.example.qrscanner_appproject.data.MeasurementLiveData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MeasurementsRepository {
    private static MeasurementsRepository instance;
    private DatabaseReference myRef;
    private MeasurementLiveData measurementLiveData;
    private String key;

    private MeasurementsRepository(){}


    public static synchronized MeasurementsRepository getInstance(){
        if (instance == null)
            instance = new MeasurementsRepository();
        return instance;
    }

    public void init(String userId){
        Long tsLong = System.currentTimeMillis()/1000;
        String timestamp = tsLong.toString();

        myRef = FirebaseDatabase.getInstance().getReference().child("Measurements").child(userId).child(timestamp);
        measurementLiveData = new MeasurementLiveData(myRef);
    }

    public void saveMeasurements(Measurement measurement){
        myRef.setValue(measurement).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.i("saveMeasurement", "Data saved successfully");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("saveMeasurement", "Unable to send data" + e);
            }
        });
    }

    public void getKey(String key){
        this.key = key;
    }


}
