package com.example.foodheroes.Models;

public class NotificationSet {

    String namaMitra, jam, tanggal, umur, position, keterangan;

    public NotificationSet(){

    }

    public NotificationSet(String namaMitra, String jam, String tanggal, String umur, String position, String keterangan) {
        this.namaMitra = namaMitra;
        this.jam = jam;
        this.tanggal = tanggal;
        this.umur = umur;
        this.position = position;
        this.keterangan = keterangan;
    }

    public String getNamaMitra() {
        return namaMitra;
    }

    public void setNamaMitra(String namaMitra) {
        this.namaMitra = namaMitra;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
