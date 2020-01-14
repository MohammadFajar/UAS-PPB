package com.mohammadfajar.stiki.uas_transbus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.database.Cursor;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;


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

        listView = (ListView) findViewById(R.id.lv_history);
        db = new DatabaseHelper(this);

        isiList();

    }

    private void isiList(){
        Cursor data = db.getData();
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()){
            listData.add(data.getString(1));
        }

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(adapter);


    }
}
