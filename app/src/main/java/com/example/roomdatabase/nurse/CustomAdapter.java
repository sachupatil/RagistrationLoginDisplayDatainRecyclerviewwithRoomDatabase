package com.example.roomdatabase.nurse;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.roomdatabase.EntityClass.UserModel;
import com.example.roomdatabase.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private List<UserModel> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView petientname;

        public MyViewHolder(View view) {
            super(view);
            petientname = (TextView) view.findViewById(R.id.petientname);

        }
    }


    public CustomAdapter(List<UserModel> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.petientlist, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        UserModel movie = moviesList.get(position);
        holder.petientname.setText(movie.getName());

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
