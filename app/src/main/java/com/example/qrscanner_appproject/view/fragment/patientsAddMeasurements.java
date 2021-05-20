package com.example.qrscanner_appproject.view.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.qrscanner_appproject.R;
import com.example.qrscanner_appproject.model.Measurement;
import com.example.qrscanner_appproject.viewModel.MeasurementsViewModel;

import java.util.Calendar;


public class patientsAddMeasurements extends Fragment {

    Button dateButton, timeButton;
    TextView dateTextView, timeTextView;
    EditText tempInput, bloodInput, drugsInput, descriptionInput, healthConditionInput;
    Button saveMeasurements;
    Measurement measurement;
    MeasurementsViewModel MeasurementsViewModel;
    String patientKey;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //ViewModel
        MeasurementsViewModel = new ViewModelProvider(this).get(MeasurementsViewModel.class);
        MeasurementsViewModel.init(patientKey);


        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_patients_add_measurements, container, false);

        dateButton = view.findViewById(R.id.dateButton);
        timeButton = view.findViewById(R.id.timeButton);
        dateTextView = view.findViewById(R.id.dateTextView);
        timeTextView = view.findViewById(R.id.timeTextView);
        tempInput = view.findViewById(R.id.tempInput);
        bloodInput =  view.findViewById(R.id.bloodInput);
        drugsInput =  view.findViewById(R.id.drugsInput);
        descriptionInput =  view.findViewById(R.id.descriptionInput);
        saveMeasurements =  view.findViewById(R.id.saveMeasurementsButton);
        healthConditionInput = view.findViewById(R.id.healthConditionInput);

        //dropdown menu impl
        Context context = this.getActivity();
        String[] conditionsArray = context.getResources().getStringArray(R.array.healthConditions);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, conditionsArray);
        AutoCompleteTextView autoCompleteTextView = view.findViewById(R.id.healthConditionInput);
        autoCompleteTextView.setAdapter(adapter);


        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleDateButton();
            }
        });

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleTimeButton();
            }
        });

        saveMeasurements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println(timeTextView.getText() +"-"+ dateTextView.getText());
                createMeasurement();
                Toast.makeText(context, "Data saved successfully", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void createMeasurement(){
        String temp = String.valueOf(tempInput.getText());
        String blood = String.valueOf(bloodInput.getText());
        String drugs = String.valueOf(drugsInput.getText());
        String desc = String.valueOf(descriptionInput.getText());
        String health = String.valueOf(healthConditionInput.getText());
        String date = String.valueOf(dateTextView.getText());
        String time = String.valueOf(timeTextView.getText());

        measurement = new Measurement(temp, blood, drugs, desc, health, date, time);
        MeasurementsViewModel.saveMeasurements(measurement);


    }

    public void passPatientKey(String key){
        patientKey = key;
    }

    private void handleTimeButton() {
        Calendar calendar = Calendar.getInstance();
        int HOUR = calendar.get(Calendar.HOUR_OF_DAY);
        int MINUTE = calendar.get(Calendar.MINUTE);

        boolean is24HourFormat = DateFormat.is24HourFormat(this.getContext());

        TimePickerDialog timePickerDialog = new TimePickerDialog(this.getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                String timeString = hourOfDay + ":" + minute;
//                timeTextView.setText(timeString);

                //Time formatting
                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar1.set(Calendar.MINUTE, minute);

                CharSequence charSequence = DateFormat.format("hh:mm a", calendar1);
                timeTextView.setText(charSequence);
            }
        }, HOUR, MINUTE, is24HourFormat);

        timePickerDialog.show();
    }

    private void handleDateButton() {

        Calendar calendar = Calendar.getInstance();
        int YEAR = calendar.get(Calendar.YEAR);
        int MONTH = calendar.get(Calendar.MONTH);
        int DATE = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int date) {
//                String dateString = year + "/" + month + "/" + date;
//                dateTextView.setText(dateString);

                //Date formatting
                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.YEAR, year);
                calendar1.set(Calendar.MONTH, month);
                calendar1.set(Calendar.DATE, date);

                CharSequence dateCharSequence = DateFormat.format("EEEE, dd MMM yyy", calendar1);
                dateTextView.setText(dateCharSequence);
            }
        }, YEAR, MONTH, DATE);

        datePickerDialog.show();
    }
}