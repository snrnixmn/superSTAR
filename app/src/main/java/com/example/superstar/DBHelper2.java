package com.example.superstar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper2 extends SQLiteOpenHelper {

    //TODO Define the Database properties
    private static final String DATABASE_NAME = "overallRewards.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_OVERALL = "Overall";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TOTALSCORES = "totalScores";

    public DBHelper2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TODO CREATE TABLE Note
        String createTableSql = "CREATE TABLE " + TABLE_OVERALL + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TOTALSCORES + " INTEGER )";
        db.execSQL(createTableSql);

        Log.i("info", "created tables");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OVERALL);
        onCreate(db);
    }

    public void insertTotal(int totalScores) {
        //TODO insert the data into the database

        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();
        // Store the column name as key and the totalScores as value
        values.put(COLUMN_TOTALSCORES, totalScores);
        // Insert the row into the TABLE_TASK
        db.insert(TABLE_OVERALL, null, values);
        // Close the database connection
        db.close();
    }

    public ArrayList<OverallReward> getTotal() {
        //TODO return records in Java objects

        ArrayList<OverallReward> rewards = new ArrayList<OverallReward>();
        String selectQuery = "SELECT " + COLUMN_ID + ", "
                + COLUMN_TOTALSCORES
                + " FROM " + TABLE_OVERALL;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                int totalScores = cursor.getInt(1);
                OverallReward obj = new OverallReward(id, totalScores);
                rewards.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return rewards;
    }

    public int updateData(OverallReward data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TOTALSCORES, data.getTotalScores());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_OVERALL, values, condition, args);
        db.close();
        if (result < 1) {
            Log.d("DBHelper", "Update failed");
        }
        return result;
    }

    public int deleteData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_OVERALL, condition, args);
        db.close();
        if (result < 1) {
            Log.d("DBHelper", "Update failed");
        }
        return result;
    }

}