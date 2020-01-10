package com.mohammadfajar.stiki.uas_transbus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Thread timer = new Thread() {
            public void run() {
                try {
                    //berapalama splashscreen akan ditampilkan dalam milisecond
                    sleep(3000);
                } catch (InterruptedException e) {
                    // TODO: handle exception
                    e.printStackTrace();
                } finally {
                    //activity yang akan dijalankan setelah splashscreen selesai
                    Intent i = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(i);
                }
            }
        };
        timer.start();
    }
}
