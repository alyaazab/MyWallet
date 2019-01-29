package com.example.android.mywallet2.viewmodel;

import com.example.android.mywallet2.datamanagers.RecordDataManager;

public class RecordViewModel {

    private RecordDataManager recordDataManager;

    public void addNewRecord(String info){
        recordDataManager = new RecordDataManager();
        recordDataManager.addRecordToDatabase(info);

    }
}
