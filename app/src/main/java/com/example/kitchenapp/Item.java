package com.example.kitchenapp;

public class Item {
    String name;
    int par;
    int actual;
    String unitOfMeasurement;
    int amountNeeded;

    public Item(String name, int par, String unitOfMeasurement){
        this.name = name;
        this.par = par;
        this.unitOfMeasurement= unitOfMeasurement;
        actual=0;
        amountNeeded=0;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setPar(int par){
        this.par = par;
    }
    public void setUnitOfMeasurement(String unitOfMeasurement){
        this.unitOfMeasurement = unitOfMeasurement;
    }
    public void setActual(int actual){
        this.actual = actual;
    }

    public String getName(){
        return name;
    }
    public int getPar(){
        return par;
    }
    public String getUnitOfMeasurement(){
        return unitOfMeasurement;
    }
    public int getActual(){
        return actual;
    }

}
