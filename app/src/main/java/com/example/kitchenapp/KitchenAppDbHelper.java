package com.example.kitchenapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class KitchenAppDbHelper extends SQLiteOpenHelper {


    // If you change the database schema, you must increment the database version.
    //database name
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "KitchenApp.db";
    //Table Name
    public static final String TABLE_NAME = "kitchenAppDb";
    //columns
    public static final String CATEGORY = "category";
    public static final String NAME_COLUMN = "name";
    public static final String PAR_COLUMN = "par";
    public static final String UOM_COLUMN = "units";
    private static final String _ID = "id";
    //Dropping database
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;
    //how database looks
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + "(" +
                    _ID + " INTEGER PRIMARY KEY," +
                    CATEGORY + " TEXT," +
                    NAME_COLUMN + " TEXT," +
                    PAR_COLUMN + " INTEGER," +
                    UOM_COLUMN + " TEXT)";


    KitchenAppDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void addItem(String name, int par, String unit) throws SQLiteException {
        String category = "cat";
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CATEGORY, category);
        values.put(NAME_COLUMN, name);
        values.put(PAR_COLUMN, par);
        values.put(UOM_COLUMN, unit);
        long newRowId = database.insert(TABLE_NAME, null, values);
    }

    //checking if database is empty
    public boolean isEmpty(){
        SQLiteDatabase db = this.getReadableDatabase();
        String count = "SELECT count(*) FROM " + TABLE_NAME;
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        mcursor.close();
        if (icount > 1) {
            return  false;
        } else {
            return true;
        }
    }

    public ArrayList<Item> getItems() {
        SQLiteDatabase db = this.getReadableDatabase();

        if (!this.isEmpty()) {
            String query = "SELECT category, name, par, units FROM " + TABLE_NAME;
            Cursor cursor = db.rawQuery(query, null);
            /*
            String[] projection = {
                    NAME_COLUMN,
                    PAR_COLUMN,
                    UOM_COLUMN
            };

            Cursor cursor = db.query(
                    TABLE_NAME,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    null


            );*/
            cursor.moveToFirst();
            ArrayList<Item> items = new ArrayList<>();

            while (cursor.moveToNext()) {
                String name, unit, category;
                int par;

                /*category = (cursor.getString(cursor.getColumnIndex("category")));*/
                name = (cursor.getString(cursor.getColumnIndex("name")));
                par = (cursor.getInt(cursor.getColumnIndex("par")));
                unit = (cursor.getString(cursor.getColumnIndex("units")));
                category = "category";
                items.add(new Item(name, par, unit, category));
            }
            cursor.close();
            return items;
        } else {
            return new ArrayList<Item>();
        }
    }

        public List<String> getCategoryNames() {
            SQLiteDatabase db = this.getReadableDatabase();

            if (!this.isEmpty()) {
                String query = "SELECT category FROM " + TABLE_NAME;
                Cursor cursor = db.rawQuery(query, null);

                List<String> categories = new ArrayList<String>();

                while (cursor.moveToNext()) {
                    String category;

                    categories.add((cursor.getString(cursor.getColumnIndex("category"))));
                }
                cursor.close();
                return categories;
            }else{
                return new ArrayList<String>();
            }

        }
            public void clearTable () {
                SQLiteDatabase db = this.getWritableDatabase();
                db.execSQL(SQL_DELETE_ENTRIES);
                db.execSQL(SQL_CREATE_ENTRIES);

            }

}