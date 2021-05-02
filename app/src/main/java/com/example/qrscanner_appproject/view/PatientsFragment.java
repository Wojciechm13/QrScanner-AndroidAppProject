package com.example.qrscanner_appproject.view;

import android.graphics.Canvas;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.qrscanner_appproject.R;
import com.example.qrscanner_appproject.data.Patient;
import com.example.qrscanner_appproject.viewModel.PatientsViewModel;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;


public class PatientsFragment extends Fragment implements RecyclerViewClickInterface {


    //ViewModel
    PatientsViewModel patientsViewModel;
    //Recycler view
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;

    //fragments
    com.example.qrscanner_appproject.view.patientsDetailsFragment patientsDetailsFragment = new patientsDetailsFragment();



    public PatientsFragment() {
        // Required empty public constructor
    }


    public static PatientsFragment newInstance() {
        PatientsFragment fragment = new PatientsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //ViewModel
        patientsViewModel = new ViewModelProvider(this).get(PatientsViewModel.class);
        patientsViewModel.init();
        patientsViewModel.getPatientsLiveData().observe(this.getViewLifecycleOwner(), new Observer<ArrayList<Patient>>() {
            @Override
            public void onChanged(ArrayList<Patient> patients) {
                recyclerAdapter.notifyDataSetChanged();
            }
        });
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_patients, container, false);


        //Init of recyclerView
        recyclerView = view.findViewById(R.id.recyclerView);
//        recyclerAdapter = new RecyclerAdapter(patientsList, this);
        recyclerAdapter = new RecyclerAdapter(patientsViewModel.getPatientsLiveData().getValue(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(recyclerAdapter);

        //Defining the divider between the rows
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);



       ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
       itemTouchHelper.attachToRecyclerView(recyclerView);



        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    String deletedPatient = null;

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            int position = viewHolder.getAdapterPosition();

            switch (direction){
                //Swipe from Right to Left
                case ItemTouchHelper.LEFT:
                    //Assigning patient to deleted patient to display it later in SNACKBAR
                   // deletedPatient = patientsList.get(position);

                    //patientsList.remove(position);
                    recyclerAdapter.notifyItemRemoved(position);
                    //Snackbar init
                    Snackbar.make(recyclerView, deletedPatient, BaseTransientBottomBar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                           // patientsList.add(position, deletedPatient);
                            recyclerAdapter.notifyItemInserted(position);
                        }
                    }).show();
                    break;

                //Swipe from Left to Right
                //Todo: Add some action to swipe
                case ItemTouchHelper.RIGHT:
                    break;
            }
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            //custom library from github link in notion
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(recyclerView.getContext(), R.color.colorRed))
                    .addSwipeLeftActionIcon(R.drawable.ic_baseline_delete_24)
                    .addSwipeRightBackgroundColor(ContextCompat.getColor(recyclerView.getContext(), R.color.green))
                    .addSwipeRightActionIcon(R.drawable.ic_baseline_star_24)
                    .create()
                    .decorate();

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

    //Method for handling a click on a position from recyclerView
    @Override
    public void onItemClick(int position) {
//        FragmentTransaction transaction = getFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
//        transaction.replace(R.id.fragmentContainer, patientsDetailsFragment ); // give your fragment container id in first parameter
//        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//        transaction.commit();

    }

    //Method for handling a longClick on a position from recyclerView
    @Override
    public void onLongItemClick(int position) {
    //implement something here...
    }


}