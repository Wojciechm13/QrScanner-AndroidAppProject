package com.example.qrscanner_appproject.view.fragment;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qrscanner_appproject.R;
import com.example.qrscanner_appproject.data.Measurement;

import java.util.ArrayList;

public class MeasurementsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Activity context;
    ArrayList<Measurement> measurements;

    public MeasurementsRecyclerAdapter(Activity context, ArrayList<Measurement> measurements) {
        this.context = context;
        this.measurements = measurements;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.measurement_recycler_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Measurement measurement = measurements.get(position);
        RecyclerViewHolder viewHolder = (RecyclerViewHolder)holder;

        viewHolder.dateTime.setText(measurement.getDate()+"/"+measurement.getTime());
        viewHolder.healthCond.setText(measurement.getHealthCondition());
        viewHolder.temp.setText(measurement.getTemp());
        viewHolder.blood.setText(measurement.getBloodPressure());
        viewHolder.drugs.setText(measurement.getGivenDrugs());
        viewHolder.desc.setText(measurement.getDescription());

//        viewHolder.dateTime.setText("15.05.2021 / 15:21");
//        viewHolder.healthCond.setText("healthCond");
//        viewHolder.temp.setText("temp");
//        viewHolder.blood.setText("blood");
//        viewHolder.drugs.setText("drugs");
//        viewHolder.desc.setText("desc");
    }

    @Override
    public int getItemCount() {
        return measurements.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder{
        TextView dateTime, healthCond, temp, blood, drugs, desc;

        public RecyclerViewHolder(@NonNull View itemView){
            super(itemView);
            dateTime = itemView.findViewById(R.id.dateTimeMeasurementHistory);
            healthCond = itemView.findViewById(R.id.healthConditionMeasurementHistory);
            temp = itemView.findViewById(R.id.tempMeasurementHistory);
            blood = itemView.findViewById(R.id.bloodMeasurementHistory);
            drugs = itemView.findViewById(R.id.drugsMeasurementHistory);
            desc = itemView.findViewById(R.id.descriptionMeasurementHistory);
        }
    }
}
