package com.example.go4lunch.ui.workmatesList;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.go4lunch.R;

public class WorkmatesListFragment extends Fragment {

    private WorkmatesListViewModel workmatesViewModel;

    public static WorkmatesListFragment newInstance() {
        return new WorkmatesListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workmates, container, false);
    }


}