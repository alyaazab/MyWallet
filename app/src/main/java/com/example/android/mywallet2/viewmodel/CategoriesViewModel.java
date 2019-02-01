package com.example.android.mywallet2.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.example.android.mywallet2.datamanagers.CategoriesDataManager;
import com.example.android.mywallet2.model.Category;

import java.util.List;

public class CategoriesViewModel extends ViewModel {

    private MediatorLiveData<List<Category>> categoriesLiveData;

    public CategoriesViewModel() {
        categoriesLiveData = new MediatorLiveData<>();
    }

    public LiveData<List<Category>> getCategories(){
        if(categoriesLiveData.getValue() == null){
            CategoriesDataManager dataManager = new CategoriesDataManager();
            categoriesLiveData.addSource(dataManager.getCategoriesFromDatabase(),
                    new Observer<List<Category>>() {
                @Override
                public void onChanged(@Nullable List<Category> skills) {
                    categoriesLiveData.setValue(skills);
                }
            });
        }
        return categoriesLiveData;
    }
}
