package com.example.android.mywallet2.datamanagers;

import com.example.android.mywallet2.model.record.DummyRecord;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RecordDataManager {
    private DatabaseReference databaseReference;

    public void addRecordToDatabase(double amount, String info){

        DummyRecord dummyRecord = new DummyRecord(info, amount);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userID = user.getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(userID).setValue(dummyRecord);
    }
}
