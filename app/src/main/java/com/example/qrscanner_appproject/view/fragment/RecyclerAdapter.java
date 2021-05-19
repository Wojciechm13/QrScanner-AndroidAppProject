package com.example.qrscanner_appproject.view.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qrscanner_appproject.R;
import com.example.qrscanner_appproject.data.Patient;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {


    ArrayList<Patient> patientsList;
    ArrayList<String> keys;


    public RecyclerAdapter(ArrayList<Patient> patientsList, ArrayList<String> keys) {
        this.patientsList = patientsList;
        this.keys = keys;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.rowCountTextView.setText(String.valueOf(position));
        holder.rowCountTextView.setText(patientsList.get(position).getName());
        holder.rowName.setText(patientsList.get(position).getLastName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity appCompatActivity = (AppCompatActivity)v.getContext();
                patientsDetailsFragment patientsDetailsFragment = new patientsDetailsFragment();
                patientsDetailsFragment.passPatient(patientsList.get(position));
                patientsDetailsFragment.passKey(keys.get(position));

                appCompatActivity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.fragmentContainer, patientsDetailsFragment).addToBackStack(null).commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return patientsList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView rowImageView;
        TextView rowName, rowCountTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rowImageView = itemView.findViewById(R.id.rowImageView);
            rowName = itemView.findViewById(R.id.rowTextView);
            rowCountTextView = itemView.findViewById(R.id.rowCountTextView);
        }


    }





}
