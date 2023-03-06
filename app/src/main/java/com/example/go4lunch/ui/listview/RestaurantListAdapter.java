package com.example.go4lunch.ui.listview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.go4lunch.R;


public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.ViewHolder> {

    private String[] users;

    public RestaurantListAdapter(String[] users){ this.users = users; }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textview = (TextView) itemView.findViewById(R.id.textview);
        }
        public TextView getTextview(){return textview;}
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_row_item,parent,false);

       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.getTextview().setText(users[position]);

    }

    @Override
    public int getItemCount() {
        return users.length;
    }


}
