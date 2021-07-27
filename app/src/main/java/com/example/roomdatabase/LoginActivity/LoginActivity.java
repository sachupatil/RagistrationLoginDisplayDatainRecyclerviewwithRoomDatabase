package com.example.roomdatabase.LoginActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roomdatabase.DaoClass.UserDao;
import com.example.roomdatabase.EntityClass.UserModeler;
import com.example.roomdatabase.GetData;
import com.example.roomdatabase.MainActivity;
import com.example.roomdatabase.R;
import com.example.roomdatabase.UserDataBase;
import com.example.roomdatabase.nurse.NurseActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

public class LoginActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText editTextEmail, editTextPassword;
    Button buttonLogin;
    TextView textViewRegister;
    UserDao db;
    UserDataBase dataBase;
    String[] users = { "Petient", "Nurse", "Admin" };
    String userlogin;
    Spinner spin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //setting the title
        toolbar.setTitle("Metroz");

        //placing toolbar in place of actionbar


        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        spin = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);


        textViewRegister = findViewById(R.id.textViewRegister);

        dataBase = Room.databaseBuilder(this, UserDataBase.class, "mi-database.db")
                .allowMainThreadQueries()
                .build();

        db = dataBase.getUserDao();


        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RagistrationActivity.class));
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                final UserModeler userer = db.getUser(email, password);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (userer != null) {
                                    userlogin = spin.getSelectedItem().toString();
                                    Log.i("LoginUsers",userlogin);
                                    Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                    if(userlogin.equals("Admin")){
                                        startActivity(new Intent(LoginActivity.this, GetData.class));
                                    }else if (userlogin.equals("Nurse")){
                                        startActivity(new Intent(LoginActivity.this, NurseActivity.class));
                                    }else if (userlogin.equals("Petient")){
                                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    }

                                }else{

                                    Toast.makeText(LoginActivity.this, "Unregistered user, or incorrect", Toast.LENGTH_SHORT).show();

                                }

                            }
                        });

                    }
                }).start();

            }
        });

    }

    public static  boolean isValidEmail(CharSequence charSequence){
        if(charSequence== null){
            return false;

        }else {
            return Patterns.EMAIL_ADDRESS.matcher(charSequence).matches();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), "Selected User: "+users[position],Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
