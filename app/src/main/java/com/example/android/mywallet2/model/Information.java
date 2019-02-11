package com.example.android.mywallet2.model;

import android.icu.text.IDNA;

import com.example.android.mywallet2.model.record.Record;

import java.util.ArrayList;
import java.util.List;

public class Information {
    private static final Information ourInstance = new Information();
    private List<Record> recordList;

    public static Information getInstance() {
        return ourInstance;
    }

    private Information() {
    }
    // TODO: another objects here (record...etc)

    public void setRecordList(List<Record> recordList) {
        this.recordList = recordList;
    }

    public List<Record> getRecordList() {
        return recordList;
    }

    public void addRecordToList(Record record) {
        recordList.add(record);
    }
}
