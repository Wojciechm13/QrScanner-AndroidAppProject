package com.example.qrscanner_appproject.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.qrscanner_appproject.CaptureActivity;
import com.example.qrscanner_appproject.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class QrScannerFragment extends Fragment implements View.OnClickListener{

   public Button scanButton;


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

            return view;
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
                //Todo: `change the TOAST to AlertDialog'
                Toast.makeText(this.getContext(), "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                //Todo: `change the TOAST to AlertDialog'
                Toast.makeText(this.getContext(), "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}