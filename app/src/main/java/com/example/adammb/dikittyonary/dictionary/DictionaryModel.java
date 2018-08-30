package com.example.adammb.dikittyonary.dictionary;

public class DictionaryModel {
    private int id;
    private String kata;
    private String arti;

    public DictionaryModel() {
    }

    public DictionaryModel(String kata, String arti) {
        this.kata = kata;
        this.arti = arti;
    }

    public DictionaryModel(int id, String kata, String arti) {
        this.id = id;
        this.kata = kata;
        this.arti = arti;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKata() {
        return kata;
    }

    public void setKata(String kata) {
        this.kata = kata;
    }

    public String getArti() {
        return arti;
    }

    public void setArti(String arti) {
        this.arti = arti;
    }
}
