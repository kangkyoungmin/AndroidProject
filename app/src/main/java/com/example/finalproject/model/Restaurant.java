package com.example.finalproject.model;

public class Restaurant {
    private int restNum; // PK
    private String restName;
    private double restLatitude;
    private double restLongitude;

    public Restaurant(int restNum, String restName, double restLatitude, double restLongitude){
        this.restNum = restNum;
        this.restName = restName;
        this.restLatitude = restLatitude;
        this.restLongitude = restLongitude;
    }

    public int getRestNum(){
        return restNum;
    }
    public String getRestName(){
        return restName;
    }
    public double getRestLatitude(){
        return restLatitude;
    }
    public double getRestLongitude(){
        return restLongitude;
    }
}