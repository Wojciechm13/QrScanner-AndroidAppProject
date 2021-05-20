package com.example.qrscanner_appproject.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.qrscanner_appproject.R;
import com.example.qrscanner_appproject.model.Patient;
import com.example.qrscanner_appproject.view.activity.CaptureActivity;
import com.example.qrscanner_appproject.viewModel.PatientsViewModel;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;


public class QrScannerFragment extends Fragment implements View.OnClickListener{

   public Button scanButton;
   PatientsViewModel patientsViewModel;
   private ArrayList<String> keys;
   private ArrayList<Patient> patientsList;
   int position;


    public QrScannerFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_qr_scanner, container, false);
            scanButton = view.findViewById(R.id.scanBtn);
            scanButton.setOnClickListener(this);

        patientsViewModel = new ViewModelProvider(this).get(PatientsViewModel.class);
        patientsViewModel.init();
        patientsViewModel.getPatientsLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<Patient>>() {
            @Override
            public void onChanged(ArrayList<Patient> patients) {
                patientsList = patients;
            }
        });
        keys = patientsViewModel.getKeys();



            return view;
    }

    public int getPatientsPosition(String scannedValue){
        for (int i = 0; i < keys.size(); i++) {
            if (keys.get(i).equals(scannedValue)){
                System.out.println("We got it!" + i);
                System.out.println(keys.get(i));
                position = i;
            }

        }
        return position;
    }

    @Override
    public void onClick(View v) {
        scanCode();
    }

    private void scanCode() {

        IntentIntegrator integrator = IntentIntegrator.forSupportFragment(QrScannerFragment.this);
        integrator.setCaptureActivity(CaptureActivity.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Scanning code...");
        integrator.initiateScan();
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data){
//        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//        if (result != null){
//            if (result.getContents() != null){
//                AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
//                builder.setMessage(result.getContents());
//                builder.setTitle("Scanning result:");
//                builder.setPositiveButton("Scan again", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        scanCode();
//                    }
//                }).setNegativeButton("Finish", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        getActivity().finish();
//                    }
//                });
//
//                AlertDialog dialog = builder.create();
//                dialog.show();
//
//            }
//            else {
//                Toast.makeText(this.getContext(), "No results", Toast.LENGTH_LONG);
//            }
//        }
//        else {
//            super.onActivityResult(requestCode, resultCode, data);
//        }
//    }

    // Get the results:
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this.getContext(), "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                changeFragment(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void changeFragment(String scannedValue){
        int position = getPatientsPosition(scannedValue);
        AppCompatActivity appCompatActivity = (AppCompatActivity)getView().getContext();
        patientsDetailsFragment patientsDetailsFragment = new patientsDetailsFragment();
        patientsDetailsFragment.passPatient(patientsList.get(position));
        patientsDetailsFragment.passKey(keys.get(position));

        appCompatActivity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.fragmentContainer, patientsDetailsFragment).addToBackStack(null).commit();
    }

}