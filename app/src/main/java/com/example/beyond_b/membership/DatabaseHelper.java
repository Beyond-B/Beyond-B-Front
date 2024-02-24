package com.example.beyond_b.membership;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "accessTokenDB";
    private static final String TABLE_ACCESS_TOKEN = "accessTokenTable";
    private static final String COLUMN_ACCESS_TOKEN = "accessToken";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ACCESS_TOKEN_TABLE = "CREATE TABLE " + TABLE_ACCESS_TOKEN + "("
                + COLUMN_ACCESS_TOKEN + " TEXT" + ")";
        db.execSQL(CREATE_ACCESS_TOKEN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCESS_TOKEN);
        onCreate(db);
    }

    public void addAccessToken(String accessToken) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ACCESS_TOKEN, accessToken);
        db.insert(TABLE_ACCESS_TOKEN, null, values);
        db.close();
    }
}

