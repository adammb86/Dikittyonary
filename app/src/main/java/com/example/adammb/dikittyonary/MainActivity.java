package com.example.adammb.dikittyonary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

import com.example.adammb.dikittyonary.dictionary.DictionaryActivity;
import com.example.adammb.dikittyonary.dictionary.DictionaryLoader;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportLoaderManager().initLoader(100,savedInstanceState,this);
    }

    @NonNull
    @Override
    public Loader onCreateLoader(int id, @Nullable Bundle args) {
        return new DictionaryLoader(MainActivity.this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader loader, Object data) {
        Intent intent = new Intent(MainActivity.this, DictionaryActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onLoaderReset(@NonNull Loader loader) {

    }
}
