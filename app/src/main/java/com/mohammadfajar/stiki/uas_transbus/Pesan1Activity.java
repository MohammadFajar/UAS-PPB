package com.mohammadfajar.stiki.uas_transbus;


import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.database.Cursor;
import android.widget.ListView;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class Pesan1Activity extends AppCompatActivity {
    public static String EXTRA_BUS = "extra_bus";
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private TextView HasilTgl;
    private EditText pickTgl;

    protected  Cursor cursor;
    DatabaseHelper db;
    ListView lihatHistory;
    String[] data;
    int hTiket;
    String dTujuan;
    Button btnPesan;
    TextView namaBus, harga;
    EditText Tanggal, tujuan;
    Spinner spbus, spjam, spjumlah;

    Bus busOrder;

    private void showDateDialog(){
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                HasilTgl.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan1);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        HasilTgl = (TextView) findViewById(R.id.tvTanggal);
        pickTgl = (EditText) findViewById(R.id.et_dateresult);

        //final String [] tujuan ={"Jombang", "Kediri", "Ngawi", "Surabaya"};
        String [] jam = {"07.00","08.30","10.00", "14.00", "15.30"};
        String [] jumlah = {"1", "2", "3", "4", "5"};
        String [] bus ={"Bagong", "Puspa", "Panda"};

        btnPesan = (Button) findViewById(R.id.btnPesan);
        harga = (TextView) findViewById(R.id.tvHarga);
        tujuan = (EditText) findViewById(R.id.ettujuan);
        String Tempholder=getIntent().getStringExtra("Listviewclickvalue");
        tujuan.setText(Tempholder);
        spbus = (Spinner) findViewById(R.id.spBus);
        spjam = (Spinner) findViewById(R.id.spinJam);
        spjumlah = (Spinner)  findViewById(R.id.SpinnerJumlah);

        /**
         * Get Extra Object
         */
        busOrder = getIntent().getParcelableExtra(EXTRA_BUS);
        harga.setText(busOrder.getHarga()+"");

        //set Spinner
        ArrayAdapter<String> adapterbus= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,bus);
        adapterbus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spbus.setAdapter(adapterbus);

        ArrayAdapter<String> adapterJam = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,jam);
        adapterJam.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spjam.setAdapter(adapterJam);

        ArrayAdapter<String> adapterJumlah= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, jumlah);
        adapterJumlah.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spjumlah.setAdapter(adapterJumlah);


        btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        HasilTgl = (TextView) findViewById(R.id.et_dateresult);
        pickTgl = (EditText) findViewById(R.id.et_dateresult);
        pickTgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });




    }
}
