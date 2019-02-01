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
import com.example.android.mywallet2.model.record.Record;
import com.example.android.mywallet2.viewmodel.RecordViewModel;

public class DebtsFragment extends Fragment {

    private FloatingActionButton buttonAddDebt;
    private RecordViewModel recordViewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_debts, container, false);

        buttonAddDebt = rootView.findViewById(R.id.buttonAddRecord);

        buttonAddDebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Prof Activity", "debt");



            }
        }
        );

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Debts");
        super.onActivityCreated(savedInstanceState);
    }
}
