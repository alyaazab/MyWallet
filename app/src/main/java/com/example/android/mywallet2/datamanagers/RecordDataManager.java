package com.example.android.mywallet2.datamanagers;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.android.mywallet2.model.Information;
import com.example.android.mywallet2.model.User;
import com.example.android.mywallet2.model.record.ExpenseRecord;
import com.example.android.mywallet2.model.record.IncomeRecord;
import com.example.android.mywallet2.model.record.Record;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RecordDataManager {
    private DatabaseReference databaseReference;
    private MutableLiveData<List<Record>> recordsLiveData;
    private FirebaseUser firebaseUser;
    private List<Record> tempList;


    public RecordDataManager() {
        recordsLiveData = new MutableLiveData<>();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        tempList = new ArrayList<>();
    }

    public void addRecordToDatabase(Record record){
        User user = User.getInstance();
        Information information = Information.getInstance();
        information.addRecordToList(record);
        user.setInformation(information);

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("users").child(firebaseUser.getUid()).child("information").setValue(information);
    }

    public LiveData<List<Record>> getRecordsFromDatabase(){
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users").
                child(firebaseUser.getUid()).child("information").child("recordList");

        databaseReference.orderByChild("data/milliseconds").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tempList.clear();

                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    if(ds.hasChild("category"))
                        tempList.add(ds.getValue(ExpenseRecord.class));
                    else
                        tempList.add(ds.getValue(IncomeRecord.class));
                }

                recordsLiveData.setValue(tempList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return recordsLiveData;
    }
}
