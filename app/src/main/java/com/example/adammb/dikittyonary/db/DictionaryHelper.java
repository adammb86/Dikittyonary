package com.example.adammb.dikittyonary.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.adammb.dikittyonary.dictionary.DictionaryModel;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.adammb.dikittyonary.db.DatabaseContract.DictionaryColumns.ARTI;
import static com.example.adammb.dikittyonary.db.DatabaseContract.DictionaryColumns.KATA;

public class DictionaryHelper {
    private Context context;
    private DatabaseHelper databaseHelper;

    private SQLiteDatabase database;

    public DictionaryHelper(Context context) {
        this.context = context;
    }

    public DictionaryHelper open() throws SQLException {
        databaseHelper=new DatabaseHelper(context);
        database=databaseHelper.getWritableDatabase();

        return this;
    }

    public void close(){
        databaseHelper.close();
    }

    public ArrayList<DictionaryModel> getDataByKata(String kata,String TABLE_NAME) {
        String result = "";
        Cursor cursor = database.query(TABLE_NAME, null, KATA + " LIKE ?",
                new String[]{kata+"%"}, null, null, _ID + " ASC", null);
        cursor.moveToFirst();
        ArrayList<DictionaryModel> arrayList = new ArrayList<>();
        DictionaryModel dictionaryModel;

        if (cursor.getCount() > 0) {
            do {
                dictionaryModel = new DictionaryModel();
                dictionaryModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                dictionaryModel.setKata(cursor.getString(cursor.getColumnIndexOrThrow(KATA)));
                dictionaryModel.setArti(cursor.getString(cursor.getColumnIndexOrThrow(ARTI)));

                arrayList.add(dictionaryModel);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }

        cursor.close();

        return arrayList;
    }

    public void beginTransaction(){
        database.beginTransaction();
    }

    public void setTransactionSuccess(){
        database.setTransactionSuccessful();
    }

    public void endTransaction(){
        database.endTransaction();
    }

    public void insertTransaction(DictionaryModel dictionaryModel,String TABLE_NAME){
        String sql = "INSERT INTO "+TABLE_NAME+" ("+KATA+", "+ARTI
                +") VALUES (?, ?)";
        SQLiteStatement stmt = database.compileStatement(sql);
        stmt.bindString(1, dictionaryModel.getKata());
        stmt.bindString(2, dictionaryModel.getArti());
        stmt.execute();
        stmt.clearBindings();

    }
}
