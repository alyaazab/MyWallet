package com.example.android.mywallet2.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.android.mywallet2.R;
import com.example.android.mywallet2.viewmodel.RecordViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private FirebaseAuth firebaseAuth;
    private TextView textViewEmail;
    private RecordViewModel recordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //set toolbar as custom toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //drawer_layout is the id of activity_profile layout
        drawerLayout = findViewById(R.id.drawer_layout);

        //navigation view is the entire drawer (list + header)
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //this takes care of the hamburger icon
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //when activity is created for the first time, open the Home fragment
        if(savedInstanceState == null)
        {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                navigationView.setCheckedItem(R.id.nav_home);
        }



//        firebaseAuth = FirebaseAuth.getInstance();
//
//        String email = firebaseAuth.getCurrentUser().getEmail();
//        textViewEmail = findViewById(R.id.textViewEmail);
//        textViewEmail.setText(email);
//        recordViewModel = new RecordViewModel();


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        //method to take care of what happens when a navigation drawer item is selected

        //switch case to figure out which item was selected, open its corresponding fragment
        //fragment_container is the area in our design where a fragment is displayed
        switch(menuItem.getItemId()){
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                break;
            case R.id.nav_budgets:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BudgetsFragment()).commit();
                break;
            case R.id.nav_debts:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DebtsFragment()).commit();
                break;
            case R.id.nav_goals:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new GoalsFragment()).commit();
                break;
            case R.id.nav_planned__payments:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PlannedPaymentsFragment()).commit();
                break;
        }

        //close the drawer
        //GravityCompat.START indicates that the drawer is on the left side of the screen
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {
        //to make sure the navigation drawer is closed if the back button is pressed
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

//    public void logout(View view) {
//        firebaseAuth.signOut();
//        finish();
//        startActivity(new Intent(this, MainActivity.class));
//    }



}
