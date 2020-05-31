package com.example.superstar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    //TODO Define the Database properties
    private static final String DATABASE_NAME = "rewards.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_REWARDS = "Rewards";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_CHORE = "chore";
    private static final String COLUMN_POINTS = "points";
    private static final String COLUMN_TOTAL = "total";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TODO CREATE TABLE Note
        String createTableSql = "CREATE TABLE " + TABLE_REWARDS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_DATE + " TEXT,"
                + COLUMN_CHORE + " TEXT,"
                + COLUMN_POINTS + " INTEGER,"
                + COLUMN_TOTAL + " INTEGER )";
        db.execSQL(createTableSql);

        Log.i("info", "created tables");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REWARDS);
        onCreate(db);
    }

    public void insertRewardSummary(String name, String date, String chore, int points, int total) {
        //TODO insert the data into the database

        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();
        // Store the column name as key and the description as value
        values.put(COLUMN_NAME, name);
        // Store the column name as key and the description as value
        values.put(COLUMN_DATE, date);
        // Store the column name as key and the date as value
        values.put(COLUMN_CHORE, chore);
        // Store the column name as key and the description as value
        values.put(COLUMN_POINTS, points);
        // Store the column name as key and the description as value
        values.put(COLUMN_TOTAL, total);
        // Insert the row into the TABLE_TASK
        db.insert(TABLE_REWARDS, null, values);
        // Close the database connection
        db.close();
    }

    public ArrayList<Reward> getRewardSummary() {
        //TODO return records in Java objects

        ArrayList<Reward> rewards = new ArrayList<Reward>();
        String selectQuery = "SELECT " + COLUMN_ID + ", "
                + COLUMN_NAME + ", "
                + COLUMN_DATE + ", "
                + COLUMN_CHORE + ", "
                + COLUMN_POINTS + ", "
                + COLUMN_TOTAL
                + " FROM " + TABLE_REWARDS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String date = cursor.getString(2);
                String chores = cursor.getString(3);
                int points = cursor.getInt(4);
                int total = cursor.getInt(5);
                Reward obj = new Reward(id, name, date, chores, points, total);
                rewards.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return rewards;
    }


//    public ArrayList<Reward> getRecent() {
//        //TODO return records in Java objects
//
//        ArrayList<ToDo> notes = new ArrayList<ToDo>();
//        String selectQuery = "SELECT " + COLUMN_ID + ", "
//                + COLUMN_DATE + ", "
//                + COLUMN_DATA
//                + " FROM " + TABLE_TODO
//                +  " LIMIT " + 10 ;
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                int id = cursor.getInt(0);
//                String date = cursor.getString(1);
//                String data = cursor.getString(2);
//                ToDo obj = new ToDo(id, date, data);
//                notes.add(obj);
//            }
//            while (cursor.moveToNext()) ;
//        }
//        cursor.close();
//        db.close();
//        return notes;
//    }

    public int updateSummary(Reward data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, data.getName());
        values.put(COLUMN_CHORE, data.getChore());
        values.put(COLUMN_POINTS, data.getPoints());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_REWARDS, values, condition, args);
        db.close();
        if (result < 1) {
            Log.d("DBHelper", "Update failed");
        }
        return result;
    }

    public int updateTotal(Reward data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TOTAL, data.getTotal());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_REWARDS, values, condition, args);
        db.close();
        if (result < 1) {
            Log.d("DBHelper", "Update failed");
        }
        return result;
    }

    public int deleteSummary(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_REWARDS, condition, args);
        db.close();
        if (result < 1) {
            Log.d("DBHelper", "Update failed");
        }
        return result;
    }

}