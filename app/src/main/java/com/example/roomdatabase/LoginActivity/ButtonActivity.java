package com.example.roomdatabase.LoginActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.roomdatabase.GetData;
import com.example.roomdatabase.MainActivity;
import com.example.roomdatabase.R;
import com.example.roomdatabase.nurse.NurseActivity;

import androidx.appcompat.app.AppCompatActivity;

public class ButtonActivity extends AppCompatActivity {

    Button admin,nurse,petient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        admin=(Button)findViewById(R.id.btadmin);
        nurse=(Button)findViewById(R.id.btnurse);
        petient=(Button)findViewById(R.id.btPetient);
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ButtonActivity.this, GetData.class);
                startActivity(i);
            }
        });
        nurse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ButtonActivity.this, NurseActivity.class);
                startActivity(i);
            }
        });
        petient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ButtonActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}