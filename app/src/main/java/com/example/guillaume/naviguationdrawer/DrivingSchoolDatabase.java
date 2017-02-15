package com.example.guillaume.naviguationdrawer;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Gaujac on 15/02/2017.
 */

public class DrivingSchoolDatabase extends SQLiteOpenHelper {

    //Database info
    private static final String DATABASE_NAME = "base.sqlite.driving_school";
    private static final int VERSION = 1;
    private static DrivingSchoolDatabase instance;

    //Table names
    private static final String TABLE_CARS = "cars";
    private static final String TABLE_DRIVING_SCHOOL = "school";
    private static final String TABLE_COURSE = "course";
    // private static final String TABLE_LIST = "list"; List course

    //Cars Table Columns
    private static final String KEY_CARS_MODEL = "model";
    private static final String KEY_CARS_MARK = "mark";
    private static final String KEY_CARS_REGISTRATION = "registration"; //Id

    //Course Table Columns
    private static final String KEY_COURSE_ID = "id";
    private static final String KEY_COURSE_ID_CARS_FK = "id_cars";
    private static final String KEY_COURSE_ID_SCHOOL_FK = "id_school";
    private static final String KEY_COURSE_KM = "km";

    //School Table Columns
    private static final String KEY_SCHOOL_ID = "id";
    private static final String KEY_SCHOOL_ADDRESS = "address";
    private static final String KEY_SCHOOL_NAME = "name";
    //private static final String KEY_SCHOOL_PICT = "name";

    //List Table Columns
    // private static final String KEY_LIST_ID ="id";
    // private static final String KEY_LIST_SCHOOL ="";
    // private static final String

    private DrivingSchoolDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    private DrivingSchoolDatabase(Context context,
                                  String name,
                                  SQLiteDatabase.CursorFactory factory,
                                  int version) {
        super(context, name, factory, version);
    }


    public static synchronized DrivingSchoolDatabase getInstance(Context context) {
        if (instance != null) {
            instance = new DrivingSchoolDatabase(context.getApplicationContext());
        }
        return instance;
    }

    //accept foreign key
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    //First time
    @Override
    public void onCreate(SQLiteDatabase db) {

        String DATABASE_CREATE_CARS = "CREATE TABLE " + TABLE_CARS +
                "(" +
                KEY_CARS_REGISTRATION + " INT PRIMARY KEY NOT NULL," + // Define a primary key
                KEY_CARS_MODEL + " TEXT," +
                KEY_CARS_MARK + " TEXT)";

        String DATABASE_CREATE_SCHOOL = "CREATE TABLE " + TABLE_DRIVING_SCHOOL +
                "(" +
                KEY_SCHOOL_ID + " INT PRIMARY KEY NOT NULL," + // Define a primary key
                KEY_SCHOOL_NAME + " TEXT NOT NULL," +
                KEY_SCHOOL_ADDRESS + " TEXT)";

        String DATABASE_CREATE_COURSE = "CREATE TABLE " + TABLE_COURSE +
                "(" +
                KEY_COURSE_ID + "INT NOT NULL" +
                KEY_COURSE_ID_CARS_FK + " INT NOT NULL REFERENCES " + TABLE_CARS + "," +
                KEY_COURSE_ID_SCHOOL_FK + " INT NOT NULL REFERENCES " + TABLE_DRIVING_SCHOOL + "," +
                KEY_COURSE_KM + " INT NOT NULL" + "," +
                "PRIMARY KEY (" + KEY_COURSE_ID + "," + KEY_COURSE_ID_CARS_FK + "," + KEY_COURSE_ID_SCHOOL_FK +
                "))";

        try {
            db.execSQL(DATABASE_CREATE_CARS);
            db.execSQL(DATABASE_CREATE_SCHOOL);
            db.execSQL(DATABASE_CREATE_COURSE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Called when db needs to be upgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_DRIVING_SCHOOL);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARS);
            onCreate(db);
        }
    }

}
