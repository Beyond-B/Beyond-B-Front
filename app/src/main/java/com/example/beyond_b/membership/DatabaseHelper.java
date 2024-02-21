package com.example.beyond_b.membership;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "accessTokenDB";
    private static final String TABLE_ACCESS_TOKEN = "accessTokenTable";
    private static final String COLUMN_ACCESS_TOKEN = "accessToken";
    private static final String COLUMN_ADDED_TIME = "addedTime";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ACCESS_TOKEN_TABLE = "CREATE TABLE " + TABLE_ACCESS_TOKEN + "("
                + COLUMN_ACCESS_TOKEN + " TEXT " + ")";

        db.execSQL(CREATE_ACCESS_TOKEN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCESS_TOKEN);
        onCreate(db);
    }

    public void addAccessToken(String accessToken) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ACCESS_TOKEN, null, null); //최신 토큰만 가져오기 위해 기존 토큰 지우기
        ContentValues values = new ContentValues();
        values.put(COLUMN_ACCESS_TOKEN, accessToken);
        db.insert(TABLE_ACCESS_TOKEN, null, values);
        db.close();
    }

    public String getAccessToken() {
        String accessToken = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ACCESS_TOKEN, new String[] { COLUMN_ACCESS_TOKEN },
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            accessToken = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return accessToken;
    }

}

