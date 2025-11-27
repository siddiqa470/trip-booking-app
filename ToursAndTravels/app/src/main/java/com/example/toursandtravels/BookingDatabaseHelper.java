package com.example.toursandtravels;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BookingDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "booking_details.db";
    public static final String TABLE_NAME = "bookings_info";
    public static final String COL_ID = "_id";
    public static final String COL_1 = "NAME";
    public static final String COL_2 = "EMAIL";
    public static final String COL_3 = "CONTACT_NO";
    public static final String COL_4 = "COUNTRY_NAME";
    public static final String COL_5 = "GENDER_VALUE";
    public static final String COL_6 = "TRAVEL_INFO";
    public static final String COL_7 = "CHECK_IN";
    public static final String COL_8 = "CHECK_OUT";
    public static final String COL_9 = "ADULTS";
    public static final String COL_10 = "CHILD";
    public static final String COL_11 = "ROOMS";
    public static final String COL_12 = "PAY_METHOD";
    public static final String COL_13 = "PLACE_NAME"; // New Column

    public BookingDatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,4); // Incremented version to 4
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME +"(_id INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, EMAIL TEXT, CONTACT_NO TEXT, COUNTRY_NAME TEXT, GENDER_VALUE TEXT, TRAVEL_INFO TEXT, CHECK_IN TEXT, CHECK_OUT TEXT, ADULTS TEXT, CHILD TEXT, ROOMS TEXT, PAY_METHOD TEXT, PLACE_NAME TEXT)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the old table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Create a new table with the updated schema
        onCreate(db);
    }
    public boolean insertData(String name,String email,String contact, String country, String gender, String travel, String checkin, String checkout, String adults, String child, String rooms, String payment, String placeName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,email);
        contentValues.put(COL_3,contact);
        contentValues.put(COL_4,country);
        contentValues.put(COL_5,gender);
        contentValues.put(COL_6,travel);
        contentValues.put(COL_7,checkin);
        contentValues.put(COL_8,checkout);
        contentValues.put(COL_9,adults);
        contentValues.put(COL_10,child);
        contentValues.put(COL_11,rooms);
        contentValues.put(COL_12,payment);
        contentValues.put(COL_13,placeName); // Add placeName to contentValues
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public Cursor getData(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE EMAIL = '" + email + "'", null);
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "_id = ?",new String[] {id});
    }

}
