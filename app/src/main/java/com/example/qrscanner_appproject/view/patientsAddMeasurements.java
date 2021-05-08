package com.example.qrscanner_appproject.view;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.qrscanner_appproject.R;
import com.example.qrscanner_appproject.data.Measurement;
import com.example.qrscanner_appproject.data.Patient;
import com.example.qrscanner_appproject.viewModel.PatientsViewModel;
import com.example.qrscanner_appproject.viewModel.addMeasurementsViewModel;
import com.google.firebase.Timestamp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class patientsAddMeasurements extends Fragment {

    Button dateButton, timeButton;
    TextView dateTextView, timeTextView;
    EditText tempInput, bloodInput, drugsInput, descriptionInput, healthConditionInput;
    Button saveMeasurements;
    Measurement measurement;
    addMeasurementsViewModel addMeasurementsViewModel;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //ViewModel
        addMeasurementsViewModel = new ViewModelProvider(this).get(addMeasurementsViewModel.class);
        addMeasurementsViewModel.init();


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

        measurement = new Measurement(temp, blood, drugs, desc, health);
        String s = measurement.toString();
        System.out.println(s);

        addMeasurementsViewModel.saveMeasurements(measurement);

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