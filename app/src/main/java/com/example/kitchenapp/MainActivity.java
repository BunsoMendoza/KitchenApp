package com.example.kitchenapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private Button addButton;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(this);
        calculateButton = findViewById(R.id.calculate_button);



        /*addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddItemActivity();
            }
        });*/
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResults();
            }
        });


    }


    public void onClick(View view) {
        TableLayout tl = (TableLayout) findViewById(R.id.table_main);
        switch(view.getId()) {
            case R.id.add_button:

                TableRow tr = new TableRow(this);
                EditText editText = new EditText(this);
                TextView tv1 = new TextView(this);
                TextView tv2 = new TextView(this);
                TextView tv3 = new TextView(this);
                format(tv1);
                format(editText);
                format(tv2);
                format(tv3);
                tv1.setText("Sample Item");
                tv2.setText("000");
                tv3.setText("1 bag");
                tr.addView(editText);
                tr.addView(tv1);
                tr.addView(tv2);
                tr.addView(tv3);
                tl.addView(tr, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                break;
            /*case R.id.Button02:
                int childCount = tl.getChildCount();
                //tl=(TableLayout)findViewById(R.id.TableLayout01);
                for (int i=0; i<childCount; i++) {
                    CheckBox checkBox = ((CheckBox)((TableRow)tl.getChildAt(i)).getChildAt(2));
                    boolean isChecked = checkBox.isChecked();
                    // TableRow currentRow = (TableRow)tl.getChildAt(i);
                    // CheckBox checkBox = (CheckBox)currentRow.getChildAt(2);
                    if(isChecked){
                        tl.removeView(checkBox);
                    }
                }
                break;*/
        }
    }
    public void format(View view){
        Resources res = getResources();
        Drawable textBackground = ResourcesCompat.getDrawable(res, R.drawable.cell_shape, null);
        view.setPadding(5, 5,5,5);
        view.setBackground(textBackground);
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
