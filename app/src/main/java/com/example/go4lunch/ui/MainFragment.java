package com.example.go4lunch.ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
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
import com.example.go4lunch.ui.listview.RestaurantListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainFragment extends Fragment {




    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

      /*  FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        RestaurantListFragment fragment = new RestaurantListFragment();
        transaction.replace(R.id.main_fragment,fragment);
        transaction.commit();*/

        BottomNavigationView bottomnavigation = (BottomNavigationView) view.findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.mapViewFragment,R.id.listViewFragment,R.id.workmatesFragment).build();
        NavController navController = NavHostFragment.findNavController(this);
        NavigationUI.setupActionBarWithNavController((MainActivity) getActivity(),navController,appBarConfiguration);
        NavigationUI.setupWithNavController(bottomnavigation, navController);



        return view;

    }

}