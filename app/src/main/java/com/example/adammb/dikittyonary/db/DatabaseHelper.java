package com.example.adammb.dikittyonary.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.example.adammb.dikittyonary.db.DatabaseContract.DictionaryColumns.ARTI;
import static com.example.adammb.dikittyonary.db.DatabaseContract.DictionaryColumns.KATA;
import static com.example.adammb.dikittyonary.db.DatabaseContract.TABLE_NAME_ENGIN;
import static com.example.adammb.dikittyonary.db.DatabaseContract.TABLE_NAME_INENG;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "dbkamus";

    private static final int DATABASE_VERSION = 1;

    public static String CREATE_TABLE_INENG = "create table "+TABLE_NAME_INENG+
            "("+_ID+" integer primary key autoincrement, "+
            KATA+" text not null, "+
            ARTI+" text not null);";

    public static String CREATE_TABLE_ENGIN = "create table "+TABLE_NAME_ENGIN+
            "("+_ID+" integer primary key autoincrement, "+
            KATA+" text not null, "+
            ARTI+" text not null);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ENGIN);
        db.execSQL(CREATE_TABLE_INENG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_ENGIN);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_INENG);
        onCreate(db);
    }
}
