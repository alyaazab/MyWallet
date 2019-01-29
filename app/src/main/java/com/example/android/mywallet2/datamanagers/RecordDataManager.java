package com.example.android.mywallet2.datamanagers;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RecordDataManager {
    private DatabaseReference databaseReference;

    public void addRecordToDatabase(String info){
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.setValue(info);

    }
}
