package com.mohammadfajar.stiki.uas_transbus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper db;
    Button login, register;
    EditText userName, password, passKonfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        userName = (EditText) findViewById(R.id.etUnameRegis);
        password = (EditText) findViewById(R.id.etPassRegis);
        passKonfirm = (EditText) findViewById(R.id.etPassConfRegis);
        login = (Button) findViewById(R.id.btnLoginRegis);
        register = (Button) findViewById(R.id.btnDaftarRegis);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });

        //register
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strUsername = userName.getText().toString();
                String strPass = password.getText().toString();
                String strPassKonfirm = passKonfirm.getText().toString();

                if(strPass.equals(strPassKonfirm)){
                    Boolean daftar = db.insertUser(strUsername,strPass);
                    if(daftar == true){
                        Toast.makeText(getApplicationContext(), "Berhasil Daftar", Toast.LENGTH_SHORT).show();
                        Intent loginIntent = new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(loginIntent);
                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(), "GAGAL Daftar", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "Username dan Passeord Tidak Sesuai", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
