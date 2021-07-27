package com.example.roomdatabase.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roomdatabase.DaoClass.UserDao;
import com.example.roomdatabase.EntityClass.UserModeler;
import com.example.roomdatabase.R;
import com.example.roomdatabase.UserDataBase;

public class RagistrationActivity extends AppCompatActivity {

    EditText editTextUsername, editTextEmail, editTextPassword, editTextCnfPassword;
    Button buttonRegister;

    TextView textViewLogin;
    private UserDao userDao;
    SharedPreferences sharedpreferences;
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ragistration);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextCnfPassword = findViewById(R.id.editTextCnfPassword);
        buttonRegister = findViewById(R.id.buttonRegister);

        textViewLogin = findViewById(R.id.textViewLogin);

        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("username", String.valueOf(editTextUsername));
        editor.apply();
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RagistrationActivity.this, LoginActivity.class));
            }
        });

        userDao = Room.databaseBuilder(this, UserDataBase.class, "mi-database.db").fallbackToDestructiveMigration()
                .build().getUserDao();

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userName = editTextUsername.getText().toString().trim();
                final String email = editTextEmail.getText().toString().trim();
                final String password = editTextPassword.getText().toString().trim();
                final String passwordConf = editTextCnfPassword.getText().toString().trim();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (password.equals(passwordConf)) {
                            UserModeler user = new UserModeler(userName,password,email);
                            userDao.insert(user);
                            Intent moveToLogin = new Intent(RagistrationActivity.this, LoginActivity.class);
                            startActivity(moveToLogin);

                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(RagistrationActivity.this, "something wrong", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }
                }).start();

            }
        });
    }
}
