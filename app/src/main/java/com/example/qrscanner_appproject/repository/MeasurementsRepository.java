package com.example.qrscanner_appproject.repository;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.qrscanner_appproject.data.Measurement;
import com.example.qrscanner_appproject.data.MeasurementLiveData;
import com.example.qrscanner_appproject.data.Patient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MeasurementsRepository {
    private static MeasurementsRepository instance;
    private DatabaseReference myRef;
    private MutableLiveData<Measurement> measurement = new MutableLiveData<>();
    private String timestamp;
    private MutableLiveData<ArrayList<Measurement>> measurements = new MutableLiveData<>();

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

    public MutableLiveData<ArrayList<Measurement>> getAllMeasurement(){
        //loadAllMeasurements();
        return measurements;
    }



    public void loadLatestMeasurement(String key) {
        Query ref = FirebaseDatabase.getInstance("https://qrscannerandroidapp-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Measurements").child(key).orderByValue().limitToLast(1);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!"+key);
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

//    public void loadAllMeasurements() {
//        Query ref = FirebaseDatabase.getInstance("https://qrscannerandroidapp-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Measurements").child("MZh5gS7C3w6QdnzBcEI").orderByValue();
//
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
//                    measurements.add(snapshot.getValue(Measurement.class));
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }


}
