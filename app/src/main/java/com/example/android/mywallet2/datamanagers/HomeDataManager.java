//package com.example.android.mywallet2.datamanagers;
//
//import android.arch.lifecycle.LiveData;
//import android.arch.lifecycle.MutableLiveData;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//
//import com.example.android.mywallet2.model.record.ExpenseRecord;
//import com.example.android.mywallet2.model.record.IncomeRecord;
//import com.example.android.mywallet2.model.record.Record;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.ChildEventListener;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class HomeDataManager {
//
//    private MutableLiveData<List<Record>> recordsLiveData;
//    private DatabaseReference databaseReference;
//    private FirebaseUser firebaseUser;
//    private List<Record> tempList;
//
//
//    public HomeDataManager() {
//        recordsLiveData = new MutableLiveData<>();
//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        tempList = new ArrayList<>();
//    }
//
//    public LiveData<List<Record>> getRecordsFromDatabase(){
//        databaseReference = FirebaseDatabase.getInstance().getReference().child("users").
//                child(firebaseUser.getUid()).child("Information").child("recordList");
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                tempList.clear();
//
//                for(DataSnapshot ds : dataSnapshot.getChildren()){
//                    if(ds.hasChild("category"))
//                        tempList.add(ds.getValue(ExpenseRecord.class));
//                    else
//                        tempList.add(ds.getValue(IncomeRecord.class));
//                }
//
//                recordsLiveData.setValue(tempList);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//        return recordsLiveData;
//    }
//
//}
