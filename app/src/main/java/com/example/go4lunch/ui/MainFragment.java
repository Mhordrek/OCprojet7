package com.example.go4lunch.ui;


import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.go4lunch.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;


public class MainFragment extends Fragment {

    private AppBarConfiguration mAppBarConfiguration;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        setupBottomNavigationView(view);

        DrawerLayout drawerLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) view.findViewById(R.id.drawer_nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_yourlunch,R.id.nav_settings,R.id.nav_logout
        )
                .setOpenableLayout(drawerLayout)
                .build();

        NavHostFragment navHostFragment = (NavHostFragment) this.getChildFragmentManager().findFragmentById(R.id.nav_host_main_fragment);
        NavController navController = navHostFragment.getNavController();
        /*NavigationUI.setupActionBarWithNavController((AppCompatActivity) getContext(),navController,mAppBarConfiguration);*/
        NavigationUI.setupWithNavController(navigationView,navController);

        return view;

    }

    private void setupBottomNavigationView(View view){
        NavHostFragment navHostFragment = (NavHostFragment) this.getChildFragmentManager().findFragmentById(R.id.nav_host_main_fragment);
        NavController navController = navHostFragment.getNavController();
        BottomNavigationView bottomNav = view.findViewById(R.id.nav_view);
        NavigationUI.setupWithNavController(bottomNav, navController);
        bottomNav.setSelectedItemId(R.id.mapViewFragment);
    }

}