package com.mohammadfajar.stiki.uas_transbus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.BatchUpdateException;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper db;
    Button login, register;
    EditText userName, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);
        userName = (EditText) findViewById(R.id.etUsername);
        password = (EditText) findViewById(R.id.etPass);
        login = (Button) findViewById(R.id.btnLogin);
        register = (Button) findViewById(R.id.btnRegister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
                finish();
            }
        });

        //login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strUsername = userName.getText().toString();
                String strPass = password.getText().toString();
                Boolean masuk = db.cekLogin(strUsername, strPass);
                if(masuk == true){
                    Boolean updateSession = db.upgradeSession("ada", 1);
                    if(updateSession == true){
                        Toast.makeText(getApplicationContext(),"Berhasil Masuk", Toast.LENGTH_SHORT).show();
                        Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(mainIntent);
                        finish();
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"GAGAL Masuk", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
