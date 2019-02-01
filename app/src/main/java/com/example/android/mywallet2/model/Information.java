package com.example.android.mywallet2.model;

import com.example.android.mywallet2.model.record.Record;

import java.util.ArrayList;
import java.util.List;

public class Information {

    // TODO: another objects here (record...etc)

    private ArrayList<Record> recordList;

    public Information(ArrayList<Record> recordList) {
        this.recordList = recordList;
    }

    public void addRecordToList(Record record) {
        recordList.add(record);
    }
}
