package com.example.go4lunch.ui.workmates;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.go4lunch.R;

public class WorkmatesFragment extends Fragment {

    private WorkmatesViewModel workmatesViewModel;

    public static WorkmatesFragment newInstance() {
        return new WorkmatesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workmates, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        workmatesViewModel = new ViewModelProvider(this).get(WorkmatesViewModel.class);
        // TODO: Use the ViewModel
    }

}