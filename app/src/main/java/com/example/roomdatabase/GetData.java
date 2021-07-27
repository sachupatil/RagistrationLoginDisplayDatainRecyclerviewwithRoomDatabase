
package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.example.roomdatabase.Adapter.UserAdapter;
import com.example.roomdatabase.EntityClass.UserModel;
import com.example.roomdatabase.LoginActivity.LoginActivity;
import com.example.roomdatabase.nurse.NurseActivity;

import java.util.ArrayList;
import java.util.List;

public class GetData extends AppCompatActivity {


    RecyclerView recyclerview;

    private List<UserModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data);
        recyclerview = findViewById(R.id.recyclerview);
        TextView logout=findViewById(R.id.logout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //setting the title
        toolbar.setTitle("Admin");

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(GetData.this, LoginActivity.class);
                startActivity(i);
                finish();

            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                list = new ArrayList<>();
                list = DatabaseClass.getDatabase(getApplicationContext()).getDao().getAllData();
                recyclerview.setAdapter(new UserAdapter(getApplicationContext(), list, new UserAdapter.DeleteItemClicklistner() {
                    @Override
                    public void onItemDelete(int position, int id) {
                        DatabaseClass.getDatabase(getApplicationContext()).getDao().deleteData(id);
                    }
                }));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(GetData.this, "Data is showing Reclerview", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        }).start();
    }

}