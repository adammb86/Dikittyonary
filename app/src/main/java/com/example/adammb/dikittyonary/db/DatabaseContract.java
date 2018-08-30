package com.example.adammb.dikittyonary.db;

import android.provider.BaseColumns;

public class DatabaseContract {

    public static String TABLE_NAME_INENG = "table_indonesia_english";
    public static String TABLE_NAME_ENGIN = "table_english_indonesia";

    static final class DictionaryColumns implements BaseColumns {

        static String KATA = "kata";
        static String ARTI = "arti";
    }
}
