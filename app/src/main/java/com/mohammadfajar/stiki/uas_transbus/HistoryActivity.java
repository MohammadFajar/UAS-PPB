package com.mohammadfajar.stiki.uas_transbus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.database.Cursor;
import android.widget.ListView;


public class HistoryActivity extends AppCompatActivity {
    protected  Cursor cursor;
    DatabaseHelper db;
    String[] dataTujuan, dataId;
    ListView listView;

    public static HistoryActivity hac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


    }
}
