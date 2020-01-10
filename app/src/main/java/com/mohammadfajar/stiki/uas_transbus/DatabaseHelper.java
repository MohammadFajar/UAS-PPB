package com.mohammadfajar.stiki.uas_transbus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "dbTransBus.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE session(id integer PRIMARY KEY,login text )");
        db.execSQL("CREATE TABLE user(id integer PRIMARY KEY AUTOINCREMENT, username text, password text)");
        db.execSQL("INSERT INTO session(id, login) VALUES (1, 'kosong')");

        String sql = "CREATE TABLE pesan (noBus integer PRIMARY KEY AUTOINCREMENT, tgl text null, namaBus text null, tujuan text null," +
                "jam text null, jumlah text null, harga int null);";
        Log.d("data", "onCreate:"+sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS session");
        db.execSQL("DROP TABLE IF EXISTS user");
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
}
