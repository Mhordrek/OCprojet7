package com.example.go4lunch.ui;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.go4lunch.MainActivity;
import com.example.go4lunch.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);


        NavHostFragment navHostFragment = (NavHostFragment) this.getChildFragmentManager().findFragmentById(R.id.nav_host_main_fragment);
        NavController navController = navHostFragment.getNavController();
        BottomNavigationView bottomNav = view.findViewById(R.id.nav_view);
        NavigationUI.setupWithNavController(bottomNav, navController);
        bottomNav.setSelectedItemId(R.id.mapViewFragment);

        return view;

    }

}