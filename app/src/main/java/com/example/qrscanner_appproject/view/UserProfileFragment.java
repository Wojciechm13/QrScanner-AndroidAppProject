package com.example.qrscanner_appproject.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.qrscanner_appproject.R;
import com.example.qrscanner_appproject.viewModel.UserProfileViewModel;
import com.google.firebase.auth.FirebaseUser;


public class UserProfileFragment extends Fragment {
    UserProfileViewModel viewModel;
    TextView userTextView, roleTextView;
    Button SignOutButton;



    public UserProfileFragment() {
        // Required empty public constructor
    }

    public static UserProfileFragment newInstance() {
        UserProfileFragment fragment = new UserProfileFragment();
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
        viewModel = new ViewModelProvider(this).get(UserProfileViewModel.class);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);

        userTextView = view.findViewById(R.id.nameTextView);
        roleTextView = view.findViewById(R.id.roleTextView);
        SignOutButton = view.findViewById(R.id.signOutBtn);
        fillInTextViews();

        SignOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut(view);
            }
        });
        return view;
    }

    public void signOut(View v) {
        viewModel.signOut();
    }

    private void fillInTextViews(){
        FirebaseUser user = viewModel.getCurrentUser().getValue();
        userTextView.setText(user.getDisplayName());
    }


}