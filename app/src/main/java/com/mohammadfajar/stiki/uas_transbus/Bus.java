package com.mohammadfajar.stiki.uas_transbus;

public class Bus {
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
}
