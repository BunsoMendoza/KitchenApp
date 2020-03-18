package com.example.kitchenapp;

import java.util.ArrayList;
import java.util.List;

public class Category {
    String name;
    List <Item> items;


    public Category(String name){
        this.name = name;
        items = new ArrayList<>();
    }

    public void addItemToList(Item item){

        items.add(item);
    }

    public String getName(){
        return name;
    }

}
