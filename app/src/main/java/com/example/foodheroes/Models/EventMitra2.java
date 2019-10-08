package com.example.foodheroes.Models;

public class EventMitra2 {

    String namaMitra, alamatMitra, deskripsiMitra, namaPenerima, alamatPenerima, kategori, tanggal, jam, porsi, koor;

    public EventMitra2(){

    }

    public EventMitra2(String namaMitra, String alamatMitra, String deskripsiMitra, String namaPenerima, String alamatPenerima, String kategori, String tanggal, String jam, String porsi, String koor) {
        this.namaMitra = namaMitra;
        this.alamatMitra = alamatMitra;
        this.deskripsiMitra = deskripsiMitra;
        this.namaPenerima = namaPenerima;
        this.alamatPenerima = alamatPenerima;
        this.kategori = kategori;
        this.tanggal = tanggal;
        this.jam = jam;
        this.porsi = porsi;
        this.koor = koor;
    }

    public String getNamaMitra() {
        return namaMitra;
    }

    public void setNamaMitra(String namaMitra) {
        this.namaMitra = namaMitra;
    }

    public String getAlamatMitra() {
        return alamatMitra;
    }

    public void setAlamatMitra(String alamatMitra) {
        this.alamatMitra = alamatMitra;
    }

    public String getDeskripsiMitra() {
        return deskripsiMitra;
    }

    public void setDeskripsiMitra(String deskripsiMitra) {
        this.deskripsiMitra = deskripsiMitra;
    }

    public String getNamaPenerima() {
        return namaPenerima;
    }

    public void setNamaPenerima(String namaPenerima) {
        this.namaPenerima = namaPenerima;
    }

    public String getAlamatPenerima() {
        return alamatPenerima;
    }

    public void setAlamatPenerima(String alamatPenerima) {
        this.alamatPenerima = alamatPenerima;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getPorsi() {
        return porsi;
    }

    public void setPorsi(String porsi) {
        this.porsi = porsi;
    }

    public String getKoor() {
        return koor;
    }

    public void setKoor(String koor) {
        this.koor = koor;
    }
}
