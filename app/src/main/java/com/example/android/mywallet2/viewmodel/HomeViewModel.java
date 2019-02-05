package com.example.android.mywallet2.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.example.android.mywallet2.datamanagers.HomeDataManager;
import com.example.android.mywallet2.model.record.Record;

import java.util.List;

public class HomeViewModel extends ViewModel {

    MediatorLiveData<List<Record>> mediatorLiveData;


    public HomeViewModel() {
        mediatorLiveData = new MediatorLiveData<>();
    }

    public LiveData<List<Record>> getRecords(){
        final HomeDataManager homeDataManager = new HomeDataManager();

        mediatorLiveData.addSource(homeDataManager.getRecordsFromDatabase(), new Observer<List<Record>>() {
            @Override
            public void onChanged(@Nullable List<Record> records) {
                mediatorLiveData.setValue(records);
            }
        });

        return mediatorLiveData;

    }
}
