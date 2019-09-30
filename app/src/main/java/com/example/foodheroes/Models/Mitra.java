package com.example.foodheroes.Models;

public class Mitra {
    String namaMitra, alamatMitra, deskripsiMitra, kategori;

    public Mitra(){

    }

    public Mitra(String namaMitra, String alamatMitra, String deskripsiMitra, String kategori) {
        this.namaMitra = namaMitra;
        this.alamatMitra = alamatMitra;
        this.deskripsiMitra = deskripsiMitra;
        this.kategori = kategori;
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

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
}
