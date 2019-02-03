package com.example.android.mywallet2.viewmodel;

import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.android.mywallet2.model.record.Record;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private MediatorLiveData<List<Record>> homeLiveData;


}
