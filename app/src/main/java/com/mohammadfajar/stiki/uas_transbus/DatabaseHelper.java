package com.mohammadfajar.stiki.uas_transbus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "Transakasi";
    private static final String COL1 = "ID";
    private static final String COL2 = "tujuan";
    private static final String COL3 = "tgl";
    private static final String COL4 = "namabus";
    private static final String COL5 = "jam";
    private static final String COL6 = "jml";
    private static final String COL7 = "harga";

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("+
                    COL1 + "INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    COL2 + "TEXT, "+
                    COL3 + "TEXT, "+
                    COL4 + "TEXT, "+
                    COL5 + "TEXT, "+
                    COL6 + "TEXT, "+
                    COL7 + "TEXT);";


    public DatabaseHelper(Context context) {
        super(context, "dbTransBus.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE session(id integer PRIMARY KEY,login text )");
        db.execSQL("CREATE TABLE user(id integer PRIMARY KEY AUTOINCREMENT, username text, password text)");
        db.execSQL("INSERT INTO session(id, login) VALUES (1, 'kosong')");

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS session");
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    //check session untuk mengecek ada user masuk atau tidak
    public Boolean checkSession(String nilaiSession){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM session WHERE login=?", new String[] {nilaiSession});
        if (cursor.getCount() >0){
            return true;
        }else {
            return false;
        }
    }

    //upgrade session
    public Boolean upgradeSession(String nilaiSession, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("login", nilaiSession);
        long update = db.update("session", contentValues, "id",null);
        if (update == -1){
            return false;
        }else{
            return true;
        }
    }

    //insert user
    public Boolean insertUser(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long insert = db.insert("user", null, contentValues);
        if (insert == -1){
            return false;
        }else{
            return true;
        }
    }

    // cek login
    public  Boolean cekLogin(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE username = ? AND password = ?", new String[]{username, password});
        if (cursor.getCount() <= 0){
            return false;
        }else{
            return true;
        }
    }

    //CRUD
    //INSTERT DATA PEMESANAN TIKET
    public boolean insertData(String tujuan, String tgl, String namaBus, String jam, String jml, String harga){
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, tujuan);
        contentValues.put(COL3, tgl);
        contentValues.put(COL4, namaBus);
        contentValues.put(COL5, jam);
        contentValues.put(COL6, jml);
        contentValues.put(COL7, harga);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1){
            return false;
        }else {
            return true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

}
