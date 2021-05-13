package com.example.qrscanner_appproject.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class MeasurementLiveData extends LiveData<Measurement> {

    private final ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            Measurement measurement = snapshot.getValue(Measurement.class);
            setValue(measurement);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }

    };

    DatabaseReference databaseReference;

    public MeasurementLiveData(DatabaseReference ref){
        databaseReference = ref;
    }

    @Override
    protected void onActive() {
        super.onActive();
        databaseReference.addValueEventListener(listener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        databaseReference.removeEventListener(listener);
    }
}
