package com.example.android.mywallet2.viewmodel;

import com.example.android.mywallet2.datamanagers.RecordDataManager;

public class RecordViewModel {

    private RecordDataManager recordDataManager;

    public void addNewRecord(double amount, String info){
        recordDataManager = new RecordDataManager();
        recordDataManager.addRecordToDatabase(amount, info);

    }
}
