package com.example.kitchenapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddItemActivity extends Activity {

    EditText parInput;
    EditText itemInput;
    EditText uomInput;
    String name, par, unitOfMeasurement;
    KitchenAppDbHelper db;
    private Button addItemButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);
        db = new KitchenAppDbHelper(this);
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



    }


    public void dbItem(){
        name = itemInput.getText().toString();
        par = parInput.getText().toString();
        unitOfMeasurement = uomInput.getText().toString();
        db.addItem(name, par, unitOfMeasurement);

}


    public void addItem(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
