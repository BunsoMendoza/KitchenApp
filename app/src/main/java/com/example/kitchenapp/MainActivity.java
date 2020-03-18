package com.example.kitchenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MainActivity extends AppCompatActivity {

    private Button addButton;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TableLayout mainTable = (TableLayout) findViewById(R.id.table_main);
        addButton = findViewById(R.id.add_button);
        calculateButton = findViewById(R.id.calculate_button);
        TableRow row= new TableRow(this);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddItemActivity();
            }
        });
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResults();
            }
        });


    }
    public void openAddItemActivity(){
        Intent intent = new Intent(this, AddItemActivity.class);
        startActivity(intent);
    }

    public void calculateResults(){
        Intent intent = new Intent(this, ResultsActivity.class);
        startActivity(intent);
    }
}
