package com.example.android.mywallet2.datamanagers;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.android.mywallet2.model.Category;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CategoriesDataManager {

    private MutableLiveData<List<Category>> categoriesLiveData;
    private List<Category> temp;
    private DatabaseReference mDatabaseCategories;

    public CategoriesDataManager() {
        categoriesLiveData = new MutableLiveData<>();
        temp = new ArrayList<>();
        mDatabaseCategories = FirebaseDatabase.getInstance().getReference().child("categories");
    }


    public LiveData<List<Category>> getCategoriesFromDatabase() {

        mDatabaseCategories.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Category category;
                temp.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    category = ds.getValue(Category.class);
                    temp.add(category);
                }
                categoriesLiveData.setValue(temp);

//                if(dataSnapshot.hasChild("cat1"))
//                    Log.e("YES BITCH", "IT WORKS");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return categoriesLiveData;
    }

}
