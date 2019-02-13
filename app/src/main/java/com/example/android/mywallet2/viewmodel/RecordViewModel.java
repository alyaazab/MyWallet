package com.example.android.mywallet2.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.android.mywallet2.datamanagers.RecordDataManager;
import com.example.android.mywallet2.model.Information;
import com.example.android.mywallet2.model.User;
import com.example.android.mywallet2.model.record.Record;

import java.util.List;

public class RecordViewModel extends ViewModel {

    private RecordDataManager recordDataManager;
    MediatorLiveData<List<Record>> mediatorLiveData;
    User user;


    public RecordViewModel() {
        mediatorLiveData = new MediatorLiveData<>();
    }

    public LiveData<List<Record>> getRecords(){
        recordDataManager = new RecordDataManager();

        mediatorLiveData.addSource(recordDataManager.getRecordsFromDatabase(), new Observer<List<Record>>() {
            @Override
            public void onChanged(@Nullable List<Record> records) {
                mediatorLiveData.setValue(records);
                Information information = Information.getInstance();
                information.setRecordList(records);
                user = User.getInstance();
                user.setInformation(information);
            }
        });

        return mediatorLiveData;

    }
    public void addNewRecord(Record record){
        recordDataManager = new RecordDataManager();
        recordDataManager.addRecordToDatabase(record);

    }
}
