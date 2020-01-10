package com.mohammadfajar.stiki.uas_transbus;

import android.os.Parcel;
import android.os.Parcelable;

public class Bus implements Parcelable {
    private String namaBus;
    private String tujuan;
    private int harga;


    public Bus(String namaBus, String tujuan, int harga){
        this.namaBus = namaBus;
        this.tujuan = tujuan;
        this.harga = harga;
    }

    public String getNamBus(){
        return namaBus;
    }

    public void setNamBus(String namaBus){
        this.namaBus = namaBus;
    }

    public String getTujuan(){
        return tujuan;
    }

    public void setTujuan(String tujuan){
        this.tujuan = tujuan;
    }

    public int getHarga(){
        return harga;
    }

    public void setHarga(int harga){
        this.harga =harga;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.namaBus);
        dest.writeString(this.tujuan);
        dest.writeInt(this.harga);
    }

    protected Bus(Parcel in) {
        this.namaBus = in.readString();
        this.tujuan = in.readString();
        this.harga = in.readInt();
    }

    public static final Creator<Bus> CREATOR = new Creator<Bus>() {
        @Override
        public Bus createFromParcel(Parcel source) {
            return new Bus(source);
        }

        @Override
        public Bus[] newArray(int size) {
            return new Bus[size];
        }
    };
}
