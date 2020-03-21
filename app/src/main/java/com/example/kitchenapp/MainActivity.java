package com.example.kitchenapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
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


public class MainActivity extends AppCompatActivity {
    String itemName;
    int itemPar;
    String itemUnitOfMeasurement;
    String itemCategory;
    private Button addButton;
    private Button calculateButton;
    KitchenAppDbHelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = new KitchenAppDbHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = findViewById(R.id.add_button);
        calculateButton = findViewById(R.id.calculate_button);
        /*Bundle extras = getIntent().getExtras();

        if (extras != null) {
            itemName = extras.getString("name");
            itemPar = extras.getString("par");
            itemUnitOfMeasurement = extras.getString("unitOfMeasurement");
            createRow(itemName, itemPar, itemUnitOfMeasurement);
        }*/
        ArrayList<Item> items = new ArrayList<Item>();


        if(!db.isEmpty()){
            items = db.getItems();
            for (Item i : items) {
                itemName = i.getName();
                itemPar = i.getPar();
                itemUnitOfMeasurement = i.getUnitOfMeasurement();
                createRow(itemName, itemPar, itemUnitOfMeasurement);
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
                db.clearTable();
                finish();
                startActivity(getIntent());
            }
        });


    }


    public void createRow(String name, int par, String unit) {
        TableLayout tableLayout = (TableLayout) findViewById(R.id.table_main);
        TableRow row = new TableRow(this);
        EditText editText = new EditText(this);
        TextView nameTextView = new TextView(this);
        TextView parTextView = new TextView(this);
        TextView unitOfMeasurement = new TextView(this);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL |
                InputType.TYPE_NUMBER_FLAG_SIGNED);
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
        Intent intent = new Intent(this, ResultsActivity.class);
        startActivity(intent);
    }



}
