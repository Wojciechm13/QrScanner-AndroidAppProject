package com.example.qrscanner_appproject.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.qrscanner_appproject.model.Measurement;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MeasurementsRepository {
    private static MeasurementsRepository instance;
    private DatabaseReference myRef;
    private MutableLiveData<Measurement> measurement = new MutableLiveData<>();
    private String timestamp;
    private MutableLiveData<ArrayList<Measurement>> measurements = new MutableLiveData<>();
    private ArrayList<Measurement> measurementsArrayList = new ArrayList<>();

    private MeasurementsRepository(){}


    public static synchronized MeasurementsRepository getInstance(){
        if (instance == null)
            instance = new MeasurementsRepository();
        return instance;
    }

    public void init(String userId){
        Long tsLong = System.currentTimeMillis()/1000;
        timestamp = tsLong.toString();

        myRef = FirebaseDatabase.getInstance().getReference().child("Measurements").child(userId);
        //measurement = new MeasurementLiveData(myRef);
    }

    public void saveMeasurements(Measurement measurement){
        myRef.child(timestamp).setValue(measurement).addOnSuccessListener(new OnSuccessListener<Void>() {
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

    public MutableLiveData<Measurement> getLatestMeasurement(String key){
        loadLatestMeasurement(key);
        return measurement;
    }

    public MutableLiveData<ArrayList<Measurement>> getAllMeasurements(String key){
        if (measurementsArrayList.size() != 0) {
            measurementsArrayList.removeAll(measurementsArrayList);
        }
        loadAllMeasurements(key);
        measurements.setValue(measurementsArrayList);
        System.out.println("Repo: "+measurements.getValue().toString());
        return measurements;
    }



    public void loadLatestMeasurement(String key) {
        Query ref = FirebaseDatabase.getInstance("https://qrscannerandroidapp-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Measurements").child(key).orderByValue().limitToLast(1);
        //Query ref = myRef.orderByValue().limitToLast(1);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    measurement.setValue(snapshot.getValue(Measurement.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void loadAllMeasurements(String key) {
        Query ref = FirebaseDatabase.getInstance("https://qrscannerandroidapp-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Measurements").child(key).orderByValue();

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    measurementsArrayList.add(snapshot.getValue(Measurement.class));
                }
                System.out.println("Repo loadAllMeasurements: "+measurementsArrayList.toString());
                System.out.println("----------------------------");
                measurements.postValue(measurementsArrayList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
