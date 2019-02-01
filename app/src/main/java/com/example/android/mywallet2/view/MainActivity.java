package com.example.android.mywallet2.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.mywallet2.R;
import com.example.android.mywallet2.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);
//        FirebaseApp.initializeApp(this);
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            //start profile activity
            finish();
            startActivity(new Intent(this, ProfileActivity.class));
        }
    }

    public void registerUser(View view) {
        //extract the entered email and password
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextPassword = findViewById(R.id.editTextPassword);

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        //if email or password is empty, display appropriate message and return
        if (email.isEmpty()) {
            editTextEmail.startAnimation(shakeError());
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.isEmpty()) {
            editTextPassword.startAnimation(shakeError());
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }
        user = User.getInstance();
        user.setEmail(email);
        user.setPassword(password);

        //if we reach this part, email and password are valid
        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password).
                addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this,
                                    "User Registered Successfully", Toast.LENGTH_SHORT).show();
                            //start to profile activity
                            finish();
                            startActivity(new Intent(getApplicationContext(),
                                    ProfileActivity.class));
                            DatabaseReference db = FirebaseDatabase.getInstance().getReference()
                                    .child("users");
                            db.push().setValue(user);
                        } else {
                            Toast.makeText(MainActivity.this, "Could not register user," +
                                            " please try again",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void goToSignInPage(View view) {
        finish();
        startActivity(new Intent(this, SignInActivity.class));
    }

    public TranslateAnimation shakeError() {
        TranslateAnimation shake = new TranslateAnimation(0, 10,
                0, 0);
        shake.setDuration(500);
        shake.setInterpolator(new CycleInterpolator(7));
        return shake;
    }
}
