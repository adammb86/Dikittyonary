package com.example.adammb.dikittyonary.dictionary;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.AsyncTaskLoader;

import com.example.adammb.dikittyonary.AppPreference;
import com.example.adammb.dikittyonary.R;
import com.example.adammb.dikittyonary.db.DictionaryHelper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

import static com.example.adammb.dikittyonary.db.DatabaseContract.TABLE_NAME_ENGIN;
import static com.example.adammb.dikittyonary.db.DatabaseContract.TABLE_NAME_INENG;

public class DictionaryLoader extends AsyncTaskLoader {
    private boolean hasResult = false;
    private AppPreference appPreference;
    private DictionaryHelper dictionaryHelper;

    public DictionaryLoader(Context context) {
        super(context);

        appPreference = new AppPreference(context);
        dictionaryHelper = new DictionaryHelper(context);
        onContentChanged();
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged())
            forceLoad();
        else if (hasResult)
            deliverResult(null);
    }

    @Override
    public void deliverResult(Object data) {
        hasResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (hasResult) {
            hasResult = false;
        }
    }

    @Override
    public LinkedList<DictionaryModel> loadInBackground() {
        Boolean firstRun = appPreference.getFirstRun();

        if (firstRun) {
            LinkedList<DictionaryModel> dictionaryInEngModels = preLoadRaw(R.raw.indonesia_english);
            LinkedList<DictionaryModel> dictionaryEngInModels = preLoadRaw(R.raw.english_indonesia);

            dictionaryHelper.open();

            dictionaryHelper.beginTransaction();

            try{
                for(DictionaryModel model:dictionaryInEngModels){
                    dictionaryHelper.insertTransaction(model, TABLE_NAME_INENG);
                }
                for(DictionaryModel model:dictionaryEngInModels){
                    dictionaryHelper.insertTransaction(model, TABLE_NAME_ENGIN);
                }

                //jika semua proses telah diset success maka akan di commit ke database
                dictionaryHelper.setTransactionSuccess();
            }catch(Exception e){
                e.printStackTrace();
            }

            dictionaryHelper.endTransaction();

            dictionaryHelper.close();

            appPreference.setFirstRun(false);
        }else{
            try {
                synchronized (this) {
                    this.wait(2000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    private LinkedList<DictionaryModel> preLoadRaw(int rawPosition) {
        LinkedList<DictionaryModel> dictionaryModels = new LinkedList<>();
        String line = null;
        BufferedReader reader;

        try {
            Resources res = getContext().getResources();
            InputStream raw_dict = res.openRawResource(rawPosition);

            reader = new BufferedReader(new InputStreamReader(raw_dict));
            int count = 0;
            do {
                line = reader.readLine();
                String[] splitstr = line.split("\t");

                DictionaryModel dictionaryModel;

                dictionaryModel = new DictionaryModel(splitstr[0], splitstr[1]);
                dictionaryModels.add(dictionaryModel);
                count++;
            } while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dictionaryModels;
    }
}
