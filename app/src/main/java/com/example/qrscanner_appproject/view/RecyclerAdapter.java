package com.example.qrscanner_appproject.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qrscanner_appproject.R;
import com.example.qrscanner_appproject.data.Patient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements Filterable {

//    List<String> patientsList;
    ArrayList<Patient> patientsList;
    private RecyclerViewClickInterface recyclerViewClickInterface;
    List<String> patientsListAll;

    public RecyclerAdapter(ArrayList<Patient> patientsList, RecyclerViewClickInterface recyclerViewClickInterface) {
        this.patientsList = patientsList;
        this.recyclerViewClickInterface = recyclerViewClickInterface;
//        this.patientsListAll = new ArrayList<>(patientsList);
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
        holder.rowCountTextView.setText(String.valueOf(position));
//        holder.rowName.setText(patientsList.get(position));
        holder.rowName.setText(patientsList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return patientsList.size();
    }

    //Method for topMenus search option
    Filter filter = new Filter() {
        //run on a background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<String> filteredList = new ArrayList<>();

            if (charSequence.toString().isEmpty()){
                filteredList.addAll(patientsListAll);
            }
            else {
                for (String patient: patientsListAll) {
                    if (patient.toLowerCase().contains(charSequence.toString().toLowerCase())){
                        filteredList.add(patient);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        //run on UI thread
        @Override
        protected void publishResults(CharSequence constraint, FilterResults filterResults) {
            patientsList.clear();
//            patientsList.addAll((Collection<? extends String>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    @Override
    public Filter getFilter() {
        return filter;
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView rowImageView;
        TextView rowName, rowCountTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rowImageView = itemView.findViewById(R.id.rowImageView);
            rowName = itemView.findViewById(R.id.rowTextView);
            rowCountTextView = itemView.findViewById(R.id.rowCountTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerViewClickInterface.onItemClick(getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
//                    patientsList.remove(getAdapterPosition());
//                    notifyItemRemoved(getAdapterPosition());
                    recyclerViewClickInterface.onLongItemClick(getAdapterPosition());
                    return true;
                }
            });
        }


    }


}
