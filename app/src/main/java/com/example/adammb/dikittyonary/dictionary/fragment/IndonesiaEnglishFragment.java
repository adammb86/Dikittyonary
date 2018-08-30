package com.example.adammb.dikittyonary.dictionary.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adammb.dikittyonary.R;
import com.example.adammb.dikittyonary.db.DatabaseContract;
import com.example.adammb.dikittyonary.db.DictionaryHelper;
import com.example.adammb.dikittyonary.dictionary.DictionaryModel;

import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class IndonesiaEnglishFragment extends Fragment {
    @BindView(R.id.search_view_ineng)
    SearchView searchIneng;
    @BindView(R.id.rv_ineng)
    RecyclerView rvIneng;

    private DictionaryAdapter adapter;
    private DictionaryHelper helper;
    private LinkedList<DictionaryModel> listDictionary;
    private String TABLE_NAME= DatabaseContract.TABLE_NAME_INENG;


    public IndonesiaEnglishFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_indonesia_english, container, false);
        ButterKnife.bind(this,view);

        adapter=new DictionaryAdapter(getActivity());
        helper=new DictionaryHelper(getActivity());
        rvIneng.setLayoutManager(new LinearLayoutManager(getActivity()));

        listDictionary=new LinkedList<>();
        adapter.setListDictionary(listDictionary);
        rvIneng.setAdapter(adapter);

        searchIneng.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.equals("")){
                    listDictionary.clear();
                    adapter.setListDictionary(listDictionary);
                    return true;
                }

                helper.open();
                listDictionary.clear();

                listDictionary.addAll(helper.getDataByKata(newText,TABLE_NAME));
                helper.close();
                adapter.setListDictionary(listDictionary);

                return true;
            }
        });

        return view;
    }

}
