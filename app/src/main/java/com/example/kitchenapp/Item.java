package com.example.kitchenapp;

public class Item {
    int id;
    String name;
    String par;
    String unitOfMeasurement;


    public Item(){

        name = "name";
        par = "0";
        unitOfMeasurement= "unitOfMeasurement";

    }
    public Item(String name, String par, String unit){

        this.name = name;
        this.par = par;
        this.unitOfMeasurement = unit;

    }

    public void setName(String name){
        this.name = name;
    }
    public void setPar(String par){
        this.par = par;
    }
    public void setUnitOfMeasurement(String unitOfMeasurement){
        this.unitOfMeasurement = unitOfMeasurement;
    }


    public String getName(){
        return name;
    }
    public String getPar(){
        return par;
    }
    public String getUnitOfMeasurement(){
        return unitOfMeasurement;
    }


}
