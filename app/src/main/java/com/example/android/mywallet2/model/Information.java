package com.example.android.mywallet2.model;

import android.icu.text.IDNA;

import com.example.android.mywallet2.model.record.Record;

import java.util.ArrayList;

public class Information {
    private static final Information ourInstance = new Information();

    public static Information getInstance() {
        return ourInstance;
    }

    private Information() {
    }

    // TODO: another objects here (record...etc)

    public void setRecordList(ArrayList<Record> recordList) {
        this.recordList = recordList;
    }

    public ArrayList<Record> getRecordList() {
        return recordList;
    }

    private ArrayList<Record> recordList = new ArrayList<>();

    public Information(ArrayList<Record> recordList) {
        this.recordList = recordList;
    }

    public void addRecordToList(Record record) {
        recordList.add(record);
    }
}
