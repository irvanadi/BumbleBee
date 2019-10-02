package com.example.foodheroes.Models;

public class User {
    String Nama, Email, NumberPhone, Password;

    public User(){

    }

    public User(String nama, String email, String numberPhone, String password) {
        Nama = nama;
        Email = email;
        NumberPhone = numberPhone;
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getNumberPhone() {
        return NumberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        NumberPhone = numberPhone;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
