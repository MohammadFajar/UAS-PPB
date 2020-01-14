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
    String pTujuan,pNamaBus, ptgl, pjam, pTotal, pJml;
    Button btnPesan;
    TextView namaBus, harga, tujuan, total;

    EditText Tanggal;
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

        db = new DatabaseHelper(this);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        HasilTgl = (TextView) findViewById(R.id.tvTanggal);
        pickTgl = (EditText) findViewById(R.id.et_dateresult);

        final String [] jam = {"07.00","08.30","10.00", "14.00", "15.30"};
        final Integer [] jumlah = new Integer[] {1, 2, 3, 4, 5};

        btnPesan = (Button) findViewById(R.id.btnPesan);
        harga = (TextView) findViewById(R.id.tvHarga);
        tujuan = (TextView) findViewById(R.id.tvTujuan);
        namaBus = (TextView) findViewById(R.id.tvNamaBus);

        spjam = (Spinner) findViewById(R.id.spinJam);
        spjumlah = (Spinner)  findViewById(R.id.SpinnerJumlah);

        /**
         * Get Extra Object
         */
        busOrder = getIntent().getParcelableExtra(EXTRA_BUS);
        harga.setText(busOrder.getHarga()+"");
        tujuan.setText(busOrder.getTujuan()+"");
        namaBus.setText(busOrder.getTujuan()+"");

        ArrayAdapter<String> adapterJam = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,jam);
        adapterJam.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spjam.setAdapter(adapterJam);

        ArrayAdapter<Integer> adapterJumlah = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, jumlah);
        adapterJumlah.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spjumlah.setAdapter(adapterJumlah);


        spjumlah.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int temJum = jumlah[i];
                int jml = Integer.parseInt(harga.getText().toString());
                int total = temJum * jml;
                String output = String.valueOf(total);
                harga.setText(output);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             pNamaBus = namaBus.getText().toString();
             pTujuan = tujuan.getText().toString();
             ptgl = HasilTgl.getText().toString();
             pTotal = harga.getText().toString();
             pjam = spjam.getSelectedItem().toString();
             pJml = spjumlah.getSelectedItem().toString();


             boolean sukses = db.insertData(pTujuan, ptgl, pNamaBus, pjam, pJml, pTotal);
             if(sukses){
                Toast.makeText(getApplicationContext(), "Tiket Berhasil diPesan", Toast.LENGTH_LONG).show();
             }else{
                 Toast.makeText(getApplicationContext(), "Tiket GAGAL diPesan", Toast.LENGTH_LONG).show();
             }

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
