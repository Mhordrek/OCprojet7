package com.example.go4lunch.ui.restaurantList;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.go4lunch.R;


public class RestaurantListFragment extends Fragment {

    protected String[] users;
    RecyclerView.LayoutManager mLayoutManager;
    RestaurantListAdapter adapter;

    private static final int DATASET_COUNT = 60;


    public static RestaurantListFragment newInstance() {
        return new RestaurantListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUser();
    }

    private void initUser() {

        users = new String[DATASET_COUNT];
        for (int i = 0; i < DATASET_COUNT; i++) {
            users[i] = "This is element #" + i;
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_restaurant, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.restaurant_view_recyclerview);
        mLayoutManager = new LinearLayoutManager(getActivity());
        adapter = new RestaurantListAdapter(users);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }



}