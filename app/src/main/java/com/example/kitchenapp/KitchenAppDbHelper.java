package com.example.kitchenapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KitchenAppDbHelper extends SQLiteOpenHelper {

    ArrayList<Item> items;
    List<String> categoryNames;
    HashMap<String, ArrayList<Item>> categoryHashMap = new HashMap<>();
    // If you change the database schema, you must increment the database version.
    //database name
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "KitchenApp.db";
    //Table Name
    public static final String ITEMS = "itemDB";
    public static final String CURRENT_AMOUNT = "resultsDB";
    public static final String CATEGORY_TABLE = "categoryTable";
    //columns
    public static final String CATEGORY = "category";
    public static final String NAME_COLUMN = "name";
    public static final String PAR_COLUMN = "par";
    public static final String UOM_COLUMN = "units";
    public static final String ACTUAL_COLUMN = "actual";
    public static final String TIMESTAMP = "time";
    private static final String _ID = "id";
    //Dropping table
    private static final String DELETE_ALL_ENTRIES = "DROP TABLE IF EXISTS " + ITEMS;
    private static final String DELETE_ENTRIES = "DROP TABLE IF EXISTS " + CURRENT_AMOUNT;
    private static final String DELETE_ALL_CATEGORIES = "DROP TABLE IF EXISTS " + CATEGORY_TABLE;
    //how table looks
    private static final String CREATE_CATEGORY_TABLE =
            "CREATE TABLE " + CATEGORY_TABLE + "(" +
                    _ID + " INTEGER PRIMARY KEY," +
                    CATEGORY + " TEXT)" ;

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ITEMS + "(" +
                    _ID + " INTEGER PRIMARY KEY," +
                    CATEGORY + " TEXT," +
                    NAME_COLUMN + " TEXT," +
                    PAR_COLUMN + " INTEGER," +
                    UOM_COLUMN + " TEXT)";

    private static final String CREATE_ACTUAL_ENTRIES =
            "CREATE TABLE " + CURRENT_AMOUNT + "(" +
                    _ID + " INTEGER PRIMARY KEY," +
                    CATEGORY + " TEXT," +
                    NAME_COLUMN + " TEXT," +
                    PAR_COLUMN + " INTEGER," +
                    ACTUAL_COLUMN + " INTEGER," +
                    UOM_COLUMN + " TEXT, " +
                    TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP)";


    KitchenAppDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(CREATE_ACTUAL_ENTRIES);
        db.execSQL(CREATE_CATEGORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DELETE_ALL_ENTRIES);
        db.execSQL(DELETE_ENTRIES);
        db.execSQL(DELETE_ALL_CATEGORIES);
        onCreate(db);
    }


    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void addCategory(String category)throws SQLiteException{
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CATEGORY, category);
        long newRowId = database.insert(CATEGORY_TABLE, null, values);
    }

    public void addItem(String name, int par, String unit, String category) throws SQLiteException {

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CATEGORY, category);
        values.put(NAME_COLUMN, name);
        values.put(PAR_COLUMN, par);
        values.put(UOM_COLUMN, unit);
        long newRowId = database.insert(ITEMS, null, values);
    }

    //checking if database is empty
    public boolean isEmpty() {
        SQLiteDatabase db = this.getReadableDatabase();
        String count = "SELECT count(*) FROM " + ITEMS;
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        mcursor.close();
        if (icount > 1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean categoryIsEmpty() {
        SQLiteDatabase db = this.getReadableDatabase();
        String count = "SELECT count(*) FROM " + CATEGORY_TABLE;
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        mcursor.close();
        if (icount > 1) {
            return false;
        } else {
            return true;
        }
    }



    public HashMap<String, ArrayList<Item>> getItems() {
        SQLiteDatabase db = this.getReadableDatabase();
        categoryHashMap = new HashMap<>();

        if (!this.isEmpty()) {
            categoryNames = getCategoryNames();
            items = new ArrayList<>();
            for (String cat : categoryNames) {
                String query = "SELECT id, category, name, par, units FROM " + ITEMS + " WHERE " + CATEGORY + " = '" + cat +"'";
                Cursor cursor = db.rawQuery(query, null);
                cursor.moveToPosition(-1);

                while (cursor.moveToNext()) {


                    int par = (cursor.getInt(cursor.getColumnIndex("par")));
                    int id = (cursor.getInt(cursor.getColumnIndex("id")));
                    String name = (cursor.getString(cursor.getColumnIndex("name")));
                    String unit = (cursor.getString(cursor.getColumnIndex("units")));
                    String category = (cursor.getString(cursor.getColumnIndex("category")));
                    items.add(new Item(name, par, unit, category, id));
                }
                categoryHashMap.put(cat, items);
                items = new ArrayList<>();
                cursor.close();

            }
            return categoryHashMap;
        }
        return new HashMap<>();

    }



        public HashMap<String, ArrayList<Item>> getResults () {
            SQLiteDatabase db = this.getReadableDatabase();
            categoryHashMap = new HashMap<>();

            if (!this.isEmpty()) {
                categoryNames = getCategoryNames();
                items = new ArrayList<>();

                for (String cat : categoryNames) {
                    String query = "SELECT id, category, name, par, units, actual FROM " + CURRENT_AMOUNT +  " WHERE " + CATEGORY + " = '" + cat +"'" + "AND " + TIMESTAMP + " =  (SELECT MAX(" + TIMESTAMP +") FROM " + CURRENT_AMOUNT + ")";
                    Cursor cursor = db.rawQuery(query, null);
                    cursor.moveToPosition(-1);


                    while (cursor.moveToNext()) {

                        int par = (cursor.getInt(cursor.getColumnIndex("par")));
                        int id = (cursor.getInt(cursor.getColumnIndex("id")));
                        String name = (cursor.getString(cursor.getColumnIndex("name")));
                        String unit = (cursor.getString(cursor.getColumnIndex("units")));
                        int actual = (cursor.getInt(cursor.getColumnIndex("actual")));
                        String category = (cursor.getString(cursor.getColumnIndex("category")));
                        items.add(new Item(name, par, unit, category, id, actual));
                    }
                    categoryHashMap.put(cat, items);
                    items = new ArrayList<>();
                    cursor.close();
                }
                return categoryHashMap;
            } else {
                return new HashMap<>();
            }
        }



    public ArrayList<String> getCategoryNames() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> cats = new ArrayList<String>();
        if (!this.categoryIsEmpty()) {
            String query = "SELECT DISTINCT category FROM " + CATEGORY_TABLE;
            Cursor cursor = db.rawQuery(query, null);
            cursor.moveToPosition(-1);

            while (cursor.moveToNext()) {
                cats.add((cursor.getString(cursor.getColumnIndex("category"))));
            }
            cursor.close();

            return cats;
        }else{
        return new ArrayList<String>();}
    }

    public void addToActualDB(String name, int par, String unit, int actual, String category) throws
            SQLiteException {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CATEGORY, category);
        values.put(NAME_COLUMN, name);
        values.put(PAR_COLUMN, par);
        values.put(ACTUAL_COLUMN, actual);
        values.put(UOM_COLUMN, unit);
        long newRowId = database.insert(CURRENT_AMOUNT, null, values);
    }

    public void clearTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(DELETE_ALL_ENTRIES);
        db.execSQL(DELETE_ENTRIES);
        db.execSQL(DELETE_ALL_CATEGORIES);
        db.execSQL(CREATE_CATEGORY_TABLE);
        db.execSQL(CREATE_ACTUAL_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES);

    }


}