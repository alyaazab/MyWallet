package com.example.android.mywallet2.datamanagers;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.android.mywallet2.model.Information;
import com.example.android.mywallet2.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserDataManager {
    public void readUserFromDatabase() {
//        FirebaseUser fbUser = FirebaseAuth.getInstance().getCurrentUser();
//        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference()
//                .child("users").child(fbUser.getUid());
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                User user = dataSnapshot.getValue(User.class);
//                Log.d("USER", user.getInformation() == null ? "NULL" : "NOT NULL");
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    }
}
