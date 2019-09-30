package com.example.foodheroes.Models;

public class User {
    String Email, NumberPhone, Password;

    public User(){

    }

    public User(String email, String numberPhone, String password) {
        Email = email;
        NumberPhone = numberPhone;
        Password = password;
    }

    public String getEmail() {
        return Email;
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
