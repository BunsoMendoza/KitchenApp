package com.example.kitchenapp;

public class Item {
    String name;
    int par;
    int actual;
    String unitOfMeasurment;
    int amountNeeded;

    public Item(String name, int par, String unitOfMeasurment){
        this.name = name;
        this.par = par;
        this.unitOfMeasurment= unitOfMeasurment;
        actual=0;
        amountNeeded=0;
    }

}
