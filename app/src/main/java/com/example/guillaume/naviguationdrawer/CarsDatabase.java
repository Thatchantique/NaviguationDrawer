package com.example.guillaume.naviguationdrawer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;

/**
 * Created by Guillaume on 15/02/2017.
 */

public class CarsDatabase {

    public String KEY_CARS_MODEL = "model";
    public String KEY_CARS_MARK = "mark";
    public String KEY_CARS_REGISTRATION = "registration";

    private DrivingSchoolDatabase dbDrivingSchool;

    private SQLiteDatabase sqlite_db;

    private static final String CARS_TABLE_NAME = "cars";

    public CarsDatabase(Context context)
    {
        dbDrivingSchool = DrivingSchoolDatabase.getInstance(context);
    }

    public void open()
    {
        if(sqlite_db == null)
            sqlite_db = dbDrivingSchool.getWritableDatabase();
    }

    public void close()
    {
        sqlite_db.close();
    }

    public void insert(Cars cars)
    {
        ContentValues values = new ContentValues();
        values.put(KEY_CARS_MARK, cars.getMark());
        values.put(KEY_CARS_REGISTRATION, cars.getRegistration());
        values.put(KEY_CARS_MODEL, cars.getModel());
        sqlite_db.insert(CARS_TABLE_NAME, null, values);
    }

    public void update()
    {

    }

    public void delete()
    {

    }


    public LinkedList<Cars> selectAll()
    {
        LinkedList<Cars> cars = new LinkedList<Cars>();
        Cursor cursor = sqlite_db.rawQuery("SELECT * FROM " + CARS_TABLE_NAME, null);
        if(cursor.getCount() != 0)
        {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
                Cars current_cars = cursorToCars(cursor);
                cars.add(current_cars);
            }
        }
        cursor.close();
        return cars;
    }

    private Cars cursorToCars(Cursor c) {
        Cars cars = new Cars();
        cars.setRegistration(c.getInt(0));
        cars.setModel(c.getString(1));
        cars.setMark(c.getString(2));
        return cars;
    }
}
