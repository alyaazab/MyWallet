package com.example.android.mywallet2.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android.mywallet2.R;
import com.example.android.mywallet2.viewmodel.RecordViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private TextView textViewEmail;
//    private RecordViewModel recordViewModel;
    private RecordViewModel recordViewModel;

    public ProfileActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();

        String email = firebaseAuth.getCurrentUser().getEmail();
        textViewEmail = findViewById(R.id.textViewEmail);
        textViewEmail.setText(email);
//        recordViewModel = new RecordViewModel();


    }

    public void logout(View view) {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    public void onClickNewRecord(View view) {
        recordViewModel = new RecordViewModel();
        recordViewModel.addNewRecord("information");

    }
}





// databaseReference.child(firebaseUser.getUid()).setValue("hellloooo");