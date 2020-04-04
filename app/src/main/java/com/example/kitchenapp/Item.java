package com.example.kitchenapp;

public class Item {
    int actual;
    int id;
    String name;
    int par;
    String unitOfMeasurement;
    String category;


    public Item(){
        actual = 0;
        id = 0;
        category = "";
        name = "name";
        par = 0;
        unitOfMeasurement= "unitOfMeasurement";

    }
    public Item(String name, int par, String unit, String category, int id, int actual){
        this.actual = actual;
        this.name = name;
        this.par = par;
        this.unitOfMeasurement = unit;
        this.category = category;
        this.id = id;

    }
    public Item(String name, int par, String unit, String category, int id){
        actual = 0;
        this.name = name;
        this.par = par;
        this.unitOfMeasurement = unit;
        this.category = category;
        this.id = id;

    }
    public void setActual(int actual){ this.actual = actual;}
    public void setName(String name){
        this.name = name;
    }
    public void setPar(int par){
        this.par = par;
    }
    public void setUnitOfMeasurement(String unitOfMeasurement){
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public int getActual(){return actual;}
    public int getID(){return id;}
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
