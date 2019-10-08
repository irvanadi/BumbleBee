package com.example.foodheroes.Models;

public class Mitra {
    String namaMitra, alamatMitra, deskripsiMitra, kategori, Email, Password, phoneNumber;

    public Mitra(){

    }

    public Mitra(String namaMitra, String alamatMitra, String deskripsiMitra, String kategori, String email, String password, String phoneNumber) {
        this.namaMitra = namaMitra;
        this.alamatMitra = alamatMitra;
        this.deskripsiMitra = deskripsiMitra;
        this.kategori = kategori;
        Email = email;
        Password = password;
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
