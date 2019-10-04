package com.example.foodheroes.Models;

public class Penerima {

    String Latitude, Longitude, Name, Address;

    public Penerima(){

    }

    public Penerima(String latitude, String longitude, String name, String address) {
        Latitude = latitude;
        Longitude = longitude;
        Name = name;
        Address = address;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
