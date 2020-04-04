package com.example.kitchenapp;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;

public class ResultsActivity extends Activity {

    KitchenAppDbHelper db;
    ArrayList<Item> items = new ArrayList<Item>();
    int id;
    String itemName;
    int itemPar;
    String itemUnitOfMeasurement;
    String itemCategory;
    int itemActual;
    int needed = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);

        db = new KitchenAppDbHelper(this);

        if(!db.isEmpty()){
            items = db.getResults();
            for (Item i : items) {
                itemName = i.getName();
                itemPar = i.getPar();
                itemUnitOfMeasurement = i.getUnitOfMeasurement();
                itemActual = i.getActual();
                needed = itemPar - itemActual;
                if(needed < 0){
                    needed = 0;
                }
                createRow(itemName, itemPar, itemUnitOfMeasurement, itemActual, needed);
            }
        }

    }

    public void createRow(String name, int par, String unit , int actual, int needed) {
        TableLayout tableLayout = (TableLayout) findViewById(R.id.results_table);
        TableRow row = new TableRow(this);
        EditText editText = new EditText(this);
        TextView nameTextView = new TextView(this);
        TextView actualTextView = new TextView(this);
        TextView parTextView = new TextView(this);
        TextView unitOfMeasurement = new TextView(this);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
        editText.setHint(Integer.toString(needed));
        nameTextView.setText(name);
        actualTextView.setText(String.valueOf(actual));
        parTextView.setText(String.valueOf(par));
        unitOfMeasurement.setText(unit);
        format(nameTextView);
        format(parTextView);
        format(actualTextView);
        format(unitOfMeasurement);
        row.addView(editText);
        row.addView(nameTextView);
        row.addView(actualTextView);
        row.addView(parTextView);

        row.addView(unitOfMeasurement);
        tableLayout.addView(row, new TableLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

    }
    public void format(View view) {
        Resources res = getResources();
        Drawable textBackground = ResourcesCompat.getDrawable(res, R.drawable.cell_shape, null);
        view.setPadding(5, 5, 5, 5);
        view.setBackground(textBackground);
    }
}