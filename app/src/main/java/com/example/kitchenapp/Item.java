package com.example.kitchenapp;

public class Item {
    String name;
    int par;
    String unitOfMeasurement;


    public Item(){
        name = "name";
        par = 0;
        unitOfMeasurement= "unitOfMeasurement";

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


    public String getName(){
        return name;
    }
    public int getPar(){
        return par;
    }
    public String getUnitOfMeasurement(){
        return unitOfMeasurement;
    }


}
