package com.example.kitchenapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class AddCategoryActivity extends Activity {
    EditText categoryInput;
    KitchenAppDbHelper db;
    String category;
    private Button addCategoryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_category);
        db = new KitchenAppDbHelper(this);
        categoryInput = (EditText) findViewById(R.id.input_category);
        addCategoryButton = findViewById(R.id.addCategory);

        addCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = categoryInput.getText().toString();
                db.addCategory(category);
                openAddItemActivity();

            }
        });
    }

    public void openAddItemActivity() {
        Intent intent = new Intent(this, AddItemActivity.class);
        startActivity(intent);
    }
}
