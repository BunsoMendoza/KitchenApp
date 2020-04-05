package com.example.kitchenapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;


import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    int id;
    String itemName;
    int itemPar;
    String itemUnitOfMeasurement;
    String categoryName;
    private Button addButton;
    private Button calculateButton;
    private Button clearButton;
    KitchenAppDbHelper db;
    HashMap<String, ArrayList<Item>> categoryHashMap = new HashMap<>();

    ArrayList<Item> items = new ArrayList<Item>();
    ArrayList<Item> categoryArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = new KitchenAppDbHelper(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clearButton = findViewById(R.id.clear_button);
        addButton = findViewById(R.id.add_button);
        calculateButton = findViewById(R.id.calculate_button);
        /*Bundle extras = getIntent().getExtras();

        if (extras != null) {
            itemName = extras.getString("name");
            itemPar = extras.getString("par");
            itemUnitOfMeasurement = extras.getString("unitOfMeasurement");
            createRow(itemName, itemPar, itemUnitOfMeasurement);
        }*/



        if(!db.isEmpty()) {
            categoryHashMap = db.getItems();

            for (String category : categoryHashMap.keySet()) {
                createCategoryHeader(category);
                items = categoryHashMap.get(category);

                for (Item i : items) {

                    itemName = i.getName();
                    itemPar = i.getPar();
                    itemUnitOfMeasurement = i.getUnitOfMeasurement();
                    id = i.getID();
                    createRow(itemName, itemPar, itemUnitOfMeasurement, id);


                }
            }
        }

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
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.clearTable();
                finish();
                startActivity(getIntent());
            }
        });


    }

    public void createCategoryHeader(String categoryName) {
        TableLayout tableLayout = (TableLayout) findViewById(R.id.table_main);
        TableRow row = new TableRow(this);
        TextView header = new TextView(this);
        header.setText(categoryName);
        format(header);
        row.addView(header);
        tableLayout.addView(row, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
    }

    public void createRow(String name, int par, String unit , int rowID) {
        TableLayout tableLayout = (TableLayout) findViewById(R.id.table_main);
        TableRow row = new TableRow(this);
        EditText editText = new EditText(this);
        editText.setId(rowID);
        TextView nameTextView = new TextView(this);
        TextView parTextView = new TextView(this);
        TextView unitOfMeasurement = new TextView(this);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
        nameTextView.setText(name);
        parTextView.setText(String.valueOf(par));
        unitOfMeasurement.setText(unit);
        format(nameTextView);
        format(parTextView);
        format(unitOfMeasurement);
        row.addView(editText);
        row.addView(nameTextView);
        row.addView(parTextView);
        row.addView(unitOfMeasurement);
        tableLayout.addView(row, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));


    }

    public void format(View view) {
        Resources res = getResources();
        Drawable textBackground = ResourcesCompat.getDrawable(res, R.drawable.cell_shape, null);
        view.setPadding(5, 5, 5, 5);
        view.setBackground(textBackground);
    }

    public void openAddItemActivity() {
        Intent intent = new Intent(this, AddItemActivity.class);
        startActivity(intent);
    }


    public void calculateResults() {
        actualDB();
        Intent intent = new Intent(this, ResultsActivity.class);
        startActivity(intent);
    }

    public void actualDB(){

        EditText text;
        int actualAmount;
        for (Item i : items) {
            text = (EditText) findViewById(i.id);
            actualAmount = Integer.parseInt(text.getText().toString());
            i.actual = actualAmount;
            db.addToActualDB(i.name, i.par, i.unitOfMeasurement, i.actual);
        }



    }



}
