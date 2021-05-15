package com.example.qrscanner_appproject.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.qrscanner_appproject.R;
import com.example.qrscanner_appproject.data.Patient;
import com.example.qrscanner_appproject.viewModel.MeasurementsViewModel;


public class patientsDetailsFragment extends Fragment {

    Button addNewMeasurementsButton;
    TextView nameLastName, DOB, SSN, BloodGroup, CostOfHospitality, Temperature, BloodPressure, GivenDrugs;
    Patient patient;
    String key;
    MeasurementsViewModel measurementsViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patients_details, container, false);

        measurementsViewModel = new ViewModelProvider(this).get(MeasurementsViewModel.class);


        //Patient
        nameLastName = view.findViewById(R.id.name);
        DOB = view.findViewById(R.id.DOB);
        SSN = view.findViewById(R.id.SSN);
        BloodGroup = view.findViewById(R.id.BloodGroup);
        CostOfHospitality = view.findViewById(R.id.CostsOfHospitalization);

        //Measurements
        Temperature = view.findViewById(R.id.temperatureField);
        BloodPressure = view.findViewById(R.id.BloodPressureField);
        GivenDrugs = view.findViewById(R.id.DrugsField);

        //todo add the observe
        nameLastName.setText(patient.getLastName()+" "+patient.getName());
        DOB.setText(patient.getDateOfBirth());
        SSN.setText( "SSN: "+ String.valueOf(patient.getSSN()));
        BloodGroup.setText("Blood group: "+patient.getBloodGroup());
        CostOfHospitality.setText("Costs of hospitality: "+String.valueOf(patient.getCostsOfHospitality())+ " EURO");

        measurementsViewModel.getLatestMeasurements(key).observe(this.getActivity(), measurement -> {
            if (measurement != null){
                System.out.println(measurement.toString());
                Temperature.setText(measurement.getTemp());
                BloodPressure.setText(measurement.getBloodPressure());
                GivenDrugs.setText(measurement.getGivenDrugs());
            }
        });
        //measurementsViewModel.loadMeasurements();


        System.out.println(key);
        patientsAddMeasurements patientsAddMeasurements = new patientsAddMeasurements();
        patientsAddMeasurements.passPatientKey(key);



        // Inflate the layout for this fragment

        addNewMeasurementsButton = view.findViewById(R.id.addNewMeasurementsButton);

        addNewMeasurementsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                transaction.replace(R.id.fragmentContainer, patientsAddMeasurements ); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });
        return view;
    }

    public void passPatient(Patient patient1){
        patient = patient1;
    }

    public void passKey(String key){
        this.key = key;
    }



}