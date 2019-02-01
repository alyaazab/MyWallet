package com.example.android.mywallet2.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.mywallet2.R;
import com.example.android.mywallet2.viewmodel.RecordViewModel;

public class BudgetsFragment extends Fragment {

    private FloatingActionButton buttonAddBudget;
    private RecordViewModel recordViewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_budgets, container, false);

        buttonAddBudget = rootView.findViewById(R.id.buttonAddRecord);

        buttonAddBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Prof Activity", "budget");



            }
        }
        );

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Budgets");
        super.onActivityCreated(savedInstanceState);
    }
}
