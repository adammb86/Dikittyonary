package com.example.adammb.dikittyonary.dictionary.fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adammb.dikittyonary.R;
import com.example.adammb.dikittyonary.dictionary.DictionaryModel;

import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryAdapter.ViewHolder> {
    private Context context;
    private LinkedList<DictionaryModel> listDictionary;

    public DictionaryAdapter(Context context) {
        this.context = context;
    }

    public void setListDictionary(LinkedList<DictionaryModel> listDictionary){
        this.listDictionary=listDictionary;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kata_arti, parent, false);
        return new ViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvKata.setText(listDictionary.get(position).getKata());
        holder.tvArti.setText(listDictionary.get(position).getArti());
    }

    @Override
    public int getItemCount() {
        return listDictionary.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_kata)
        TextView tvKata;
        @BindView(R.id.tv_arti)
        TextView tvArti;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
