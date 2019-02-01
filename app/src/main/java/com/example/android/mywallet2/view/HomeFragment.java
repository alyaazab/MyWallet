package com.example.android.mywallet2.view;

import android.content.Intent;
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
import com.example.android.mywallet2.view.records.RecordActivity;
import com.example.android.mywallet2.viewmodel.RecordViewModel;

public class HomeFragment extends Fragment {

    private RecordViewModel recordViewModel;
    private FloatingActionButton buttonAddRecord;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        buttonAddRecord = rootView.findViewById(R.id.buttonAddRecord);

        buttonAddRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Log.e("Activity", "RECORD");
                    startActivity(new Intent(getContext(), RecordActivity.class));

                }
            }
        );

        return rootView;
    }


    //override this method to rename the toolbar title
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Home");
        super.onActivityCreated(savedInstanceState);
    }


}
