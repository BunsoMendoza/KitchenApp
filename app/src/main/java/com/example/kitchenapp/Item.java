package com.example.kitchenapp;

public class Item {
    int id;
    String name;
    int par;
    String unitOfMeasurement;
    String category;


    public Item(){
        category = "";
        name = "name";
        par = 0;
        unitOfMeasurement= "unitOfMeasurement";

    }
    public Item(String name, int par, String unit, String category){

        this.name = name;
        this.par = par;
        this.unitOfMeasurement = unit;
        this.category = category;

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
