package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.example.roomdatabase.EntityClass.UserModel;
import com.example.roomdatabase.LoginActivity.LoginActivity;

public class MainActivity extends AppCompatActivity {

    EditText name, phoneno, address;
    Button save, getData;
    TextView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        phoneno = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        save = findViewById(R.id.btn_save);
        logout = findViewById(R.id.logout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Petient");
       // getData = findViewById(R.id.btn_getData);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // saveData();\
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String name_txt = name.getText().toString().trim();
                        String phone_txt = phoneno.getText().toString().trim();
                        String address_txt = address.getText().toString().trim();
                        UserModel model = new UserModel();
                        model.setName(name_txt);
                        model.setAddress(address_txt);
                        model.setPhoneno(phone_txt);
                        DatabaseClass.getDatabase(getApplicationContext()).getDao().insertAllData(model);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                name.setText("");
                                phoneno.setText("");
                                address.setText("");
                                Toast.makeText(MainActivity.this, "Data Successfully Saved", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }).start();
            }
        });
    }
}