package com.example.kitchenapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AddItemActivity extends Activity {

    EditText parInput;
    EditText itemInput;
    EditText uomInput;
    Spinner spinner;
    String name, unitOfMeasurement, category;
    int par;
    KitchenAppDbHelper db;
    private Button addItemButton;
    private Button addCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);
        db = new KitchenAppDbHelper(this);
        spinner = (Spinner) findViewById(R.id.itemCategorySpinner);

            ArrayList<String> categories = db.getCategoryNames();
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, categories);
            // Drop down layout style - list view with radio button
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinner.setAdapter(adapter);


        addCategory = findViewById(R.id.addCategoryActivity);
        addItemButton = findViewById(R.id.addItem);
        parInput = (EditText) findViewById(R.id.input_par);
        itemInput = (EditText) findViewById(R.id.input_item);
        uomInput = (EditText) findViewById(R.id.input_unit_of_measurement);


        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // item.par = Integer.parseInt(parInput.getText().toString());

              dbItem();
              addItem();
            }
        });

        addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddCategory();

            }
        });

    }


    public void dbItem(){
        name = itemInput.getText().toString();
        par = Integer.parseInt(parInput.getText().toString());
        unitOfMeasurement = uomInput.getText().toString();
        category = spinner.getSelectedItem().toString();
        db.addItem(name, par, unitOfMeasurement, category);

}



    public void addCategory(){

    }

    public void addItem(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openAddCategory() {
        Intent intent = new Intent(this, AddCategoryActivity.class);
        startActivity(intent);
    }
}
