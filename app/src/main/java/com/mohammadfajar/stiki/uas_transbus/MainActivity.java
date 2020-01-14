package com.mohammadfajar.stiki.uas_transbus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.widget.ImageButton;
import android.app.Activity;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {
    private Activity activity = this;
    ImageButton btnCari, btnHistory, btnKeluar, btnTentang;
    ViewFlipper viewFlipper;
    DatabaseHelper db;
    public static MainActivity mainAc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //db login
        db = new DatabaseHelper(this);
        btnKeluar = (ImageButton) findViewById(R.id.btnlogout);
        btnCari = (ImageButton)findViewById(R.id.btnCari);
        btnHistory = (ImageButton) findViewById(R.id.btnHistory);
        btnTentang = (ImageButton) this.findViewById(R.id.btnAbout);


        Boolean checkSession = db.checkSession("ada");
        if(checkSession == false){
            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(loginIntent);
            finish();

        }

        //Logout
        btnKeluar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                 Boolean updateSession = db.upgradeSession("kosong", 1);
                 if(updateSession == true){
                     Toast.makeText(getApplicationContext(),"Berhasil Keluar",Toast.LENGTH_SHORT).show();
                     Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                     startActivity(loginIntent);
                     finish();
                 }
            }
        });

        btnHistory.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent historyIntent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(historyIntent);
            }
        });

        int images[] = {R.drawable.gambar1, R.drawable.slide2, R.drawable.slide3};

        viewFlipper = findViewById(R.id.v_flipper);

        for (int i =0; i<images.length; i++){
            fliverImages(images[i]);
        }
        for (int image: images) {
            fliverImages(image);
        }


        // Intent Halaman
        btnTentang.setOnClickListener(new klikTentang());
        btnCari.setOnClickListener(new klikPesan());
    }


    class  klikPesan implements ImageButton.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent i = new Intent(activity, CariActivity.class);
            startActivity(i);
        }
    }

    class klikTentang implements ImageButton.OnClickListener{
        public void onClick (View v)
        {
            Intent i = new Intent(activity,tentangActivity.class);
            startActivity(i);
        }
    }


    public  void  fliverImages(int images){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(images);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(this,android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this,android.R.anim.slide_out_right);

    }
}
