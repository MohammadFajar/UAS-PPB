package com.mohammadfajar.stiki.uas_transbus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class CariActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BusAdapater adapater;
    private ArrayList<Bus> busArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari);

        addData();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapater = new BusAdapater(busArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CariActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapater);
    }

    void addData(){
        busArrayList = new ArrayList<>();
        busArrayList.add(new Bus("Puspa Indah", "Kediri", 15000));
        busArrayList.add(new Bus("Bagong", "Jombang", 16000));
        busArrayList.add(new Bus("Puspa", "Kediri", 15500));
        busArrayList.add(new Bus("Panda", "Magetan", 25000));
    }
}
