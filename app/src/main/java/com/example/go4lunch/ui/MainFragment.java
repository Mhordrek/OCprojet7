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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.go4lunch.R;
import com.example.go4lunch.databinding.NavHeaderMainBinding;
import com.example.go4lunch.manager.UserManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseUser;


public class MainFragment extends Fragment {

    private AppBarConfiguration mAppBarConfiguration;
    private UserManager userManager = UserManager.getInstance();
    private NavHeaderMainBinding navHeaderMainBinding;
    private ImageView userImage;
    private TextView userName, userMail;


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
        addDataToDrawerLayout(view);
        DrawerLayout drawerLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) view.findViewById(R.id.drawer_nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_yourlunch, R.id.nav_settings, R.id.nav_logout
        )
                .setOpenableLayout(drawerLayout)
                .build();

        NavHostFragment navHostFragment = (NavHostFragment) this.getChildFragmentManager().findFragmentById(R.id.nav_host_main_fragment);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(navigationView, navController);

        return view;

    }

    private void setupBottomNavigationView(View view) {
        NavHostFragment navHostFragment = (NavHostFragment) this.getChildFragmentManager().findFragmentById(R.id.nav_host_main_fragment);
        NavController navController = navHostFragment.getNavController();
        BottomNavigationView bottomNav = view.findViewById(R.id.nav_view);
        NavigationUI.setupWithNavController(bottomNav, navController);
        bottomNav.setSelectedItemId(R.id.restaurantListFragment);
    }

    private void addDataToDrawerLayout(View view) {
        FirebaseUser currentUser = userManager.getCurrentUser();
        if (currentUser != null) {
            NavigationView navigationView = view.findViewById(R.id.drawer_nav_view);
            View headerView = navigationView.getHeaderView(0);
            userImage = headerView.findViewById(R.id.drawer_imageView);
            userName = headerView.findViewById(R.id.drawer_username);
            userMail = headerView.findViewById(R.id.drawer_user_mail);


            userName.setText(currentUser.getDisplayName());
            userMail.setText(currentUser.getEmail());
            Glide.with(getContext())
                    .load(currentUser.getPhotoUrl())
                    .apply(RequestOptions.circleCropTransform())
                    .into(userImage);

        }

    }
}