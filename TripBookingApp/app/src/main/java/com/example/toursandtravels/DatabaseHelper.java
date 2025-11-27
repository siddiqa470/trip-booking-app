package com.example.toursandtravels;// DatabaseHelper.java

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.toursandtravels.UserData;
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "register.db";
    public static final String TABLE_NAME = "register_info";
    public static final String COL_1 = "NAME";
    public static final String COL_2 = "EMAIL";
    public static final String COL_3 = "CONTACT";
    public static final String COL_4 = "PASSWORD";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, EMAIL TEXT, CONTACT TEXT, PASSWORD TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String email, String contact, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, name);
        contentValues.put(COL_2, email);
        contentValues.put(COL_3, contact);
        contentValues.put(COL_4, password);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }
    public boolean checkUser(String email,String password){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COL_1};
        String selection = COL_2 + "=? AND " + COL_4 + "=?";
        String[] selectionArgs = {email , password};
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        return count>0;
    }


    public UserData getUserData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, "NAME DESC");

        UserData userData = null;
        try {
            if (cursor != null && cursor.moveToFirst()) {
                int nameIndex = cursor.getColumnIndex(COL_1);
                int emailIndex = cursor.getColumnIndex(COL_2);
                int contactIndex = cursor.getColumnIndex(COL_3);
                int passwordIndex = cursor.getColumnIndex(COL_4);

                String name = cursor.getString(nameIndex);
                String email = cursor.getString(emailIndex);
                String contact = cursor.getString(contactIndex);
                String password = cursor.getString(passwordIndex);

                userData = new UserData(name, email, contact, password);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return userData;
    }
    public void deleteCurrentUser(String userEmail) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COL_2 + " = ?", new String[]{userEmail});
        db.close();
    }
    public boolean updateUserData(String newName, String newEmail, String newContact, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, newName);
        contentValues.put(COL_2, newEmail);
        contentValues.put(COL_3, newContact);
        contentValues.put(COL_4, newPassword);

        // Define the condition for updating the user data (e.g., based on email)
        String selection = COL_2 + " = ?";
        String[] selectionArgs = { newEmail };

        // Perform the update operation and get the number of rows affected
        int rowsAffected = db.update(TABLE_NAME, contentValues, selection, selectionArgs);

        // Close the database connection
        db.close();

        // Return true if at least one row was affected, indicating a successful update
        return rowsAffected > 0;
    }
}