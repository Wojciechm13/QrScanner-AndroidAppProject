package com.example.qrscanner_appproject.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qrscanner_appproject.R;
import com.example.qrscanner_appproject.model.Measurement;
import com.example.qrscanner_appproject.viewModel.MeasurementsViewModel;

import java.util.ArrayList;


public class MeasurementsHistory extends Fragment {

    private RecyclerView recyclerView;
    MeasurementsRecyclerAdapter measurementsRecyclerAdapter;
    MeasurementsViewModel measurementsViewModel;
    String key;


    public MeasurementsHistory() {
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_measurements_history, container, false);

        measurementsViewModel = new ViewModelProvider(this).get(MeasurementsViewModel.class);
        System.out.println("KEY: "+key);
        measurementsViewModel.init(key);
        measurementsViewModel.loadMeasurements().observe(this.getViewLifecycleOwner(), new Observer<ArrayList<Measurement>>() {
            @Override
            public void onChanged(ArrayList<Measurement> measurements) {
                measurementsRecyclerAdapter.notifyDataSetChanged();
            }
        });




        recyclerView = view.findViewById(R.id.measurementsHistoryRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        measurementsRecyclerAdapter = new MeasurementsRecyclerAdapter(this.getActivity(), measurementsViewModel.loadMeasurements().getValue());
        recyclerView.setAdapter(measurementsRecyclerAdapter);



        return view;
    }





    public void passKey(String key){
        this.key = key;
    }
}