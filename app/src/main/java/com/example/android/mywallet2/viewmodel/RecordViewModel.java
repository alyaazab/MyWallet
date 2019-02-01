package com.example.android.mywallet2.viewmodel;

import com.example.android.mywallet2.datamanagers.RecordDataManager;
import com.example.android.mywallet2.model.record.Record;

public class RecordViewModel {

    private RecordDataManager recordDataManager;

    public void addNewRecord(Record record){
        recordDataManager = new RecordDataManager();
        recordDataManager.addRecordToDatabase(record);

    }
}
