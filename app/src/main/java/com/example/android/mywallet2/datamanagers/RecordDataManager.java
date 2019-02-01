package com.example.android.mywallet2.datamanagers;

import android.icu.text.IDNA;

import com.example.android.mywallet2.model.Information;
import com.example.android.mywallet2.model.User;
import com.example.android.mywallet2.model.record.DummyRecord;
import com.example.android.mywallet2.model.record.Record;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RecordDataManager {
    private DatabaseReference databaseReference;

    public void addRecordToDatabase(Record record){
        User user = User.getInstance();
        Information information = Information.getInstance();
        information.addRecordToList(record);
        user.setInformation(information);

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("users").child(firebaseUser.getUid()).child("Information").setValue(information);
    }
}
