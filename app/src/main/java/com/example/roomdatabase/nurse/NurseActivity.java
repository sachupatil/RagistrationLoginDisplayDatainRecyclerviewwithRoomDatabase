package com.example.roomdatabase.nurse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;


import com.example.roomdatabase.Adapter.UserAdapter;
import com.example.roomdatabase.DatabaseClass;
import com.example.roomdatabase.EntityClass.UserModel;
import com.example.roomdatabase.GetData;
import com.example.roomdatabase.LoginActivity.LoginActivity;
import com.example.roomdatabase.MainActivity;
import com.example.roomdatabase.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NurseActivity extends AppCompatActivity {
    private List<PetientModel> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CustomAdapter mAdapter;

    private List<UserModel> list ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Nurse");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        TextView logout=findViewById(R.id.logout);

       // prepareMovieData();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(NurseActivity.this, LoginActivity.class);
                startActivity(i);
                finish();

            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                list = new ArrayList<>();
                list = DatabaseClass.getDatabase(getApplicationContext()).getDao().getAllData();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new CustomAdapter(list);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(NurseActivity.this);
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setAdapter(mAdapter);
                        Toast.makeText(NurseActivity.this, "Petient Data is showing", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        }).start();
    }

    private void prepareMovieData() {
        PetientModel movie = new PetientModel("Petient 1");
        movieList.add(movie);

        movie = new PetientModel("Petient 2");
        movieList.add(movie);

        movie = new PetientModel("Petient 3");
        movieList.add(movie);

        movie = new PetientModel("Petient 4");
        movieList.add(movie);

        movie = new PetientModel("Petient 5");
        movieList.add(movie);

        movie = new PetientModel("Petient 6");
        movieList.add(movie);

        movie = new PetientModel("Petient 7");
        movieList.add(movie);

        movie = new PetientModel("Petient 8");
        movieList.add(movie);

        movie = new PetientModel("Petient 9");
        movieList.add(movie);

        movie = new PetientModel("Petient 10");
        movieList.add(movie);
        movie = new PetientModel("Petient 11");
        movieList.add(movie);
        movie = new PetientModel("Petient 12");
        movieList.add(movie);
        movie = new PetientModel("Petient 13");
        movieList.add(movie);
        movie = new PetientModel("Petient 14");
        movieList.add(movie);
        movie = new PetientModel("Petient 15");
        movieList.add(movie);
        movie = new PetientModel("Petient 16");
        movieList.add(movie);
        movie = new PetientModel("Petient 17");
        movieList.add(movie);
        movie = new PetientModel("Petient 18");
        movieList.add(movie);
        movie = new PetientModel("Petient 19");
        movieList.add(movie);
        movie = new PetientModel("Petient 20");
        movieList.add(movie);
        movie = new PetientModel("Petient 21");
        movieList.add(movie);
        movie = new PetientModel("Petient 22");
        movieList.add(movie);
        movie = new PetientModel("Petient 23");
        movieList.add(movie);

        mAdapter.notifyDataSetChanged();
    }
}