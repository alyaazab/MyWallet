package com.example.android.mywallet2.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
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
import android.widget.TextView;

import com.example.android.mywallet2.R;
import com.example.android.mywallet2.model.Information;
import com.example.android.mywallet2.model.User;
import com.example.android.mywallet2.model.record.Record;
import com.example.android.mywallet2.view.records.RecordActivity;
import com.example.android.mywallet2.viewmodel.RecordViewModel;

import java.util.List;

public class HomeFragment extends Fragment {

    private RecordViewModel recordViewModel;
    private FloatingActionButton buttonAddRecord;

    private TextView recordsTextView;

    private User user;
    private Information information;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        recordsTextView = rootView.findViewById(R.id.recordsTextView);

        buttonAddRecord = rootView.findViewById(R.id.buttonAddRecord);
        buttonAddRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Log.e("Activity", "RECORD");
                    startActivity(new Intent(getContext(), RecordActivity.class));
                }
            }
        );

        recordViewModel = ViewModelProviders.of(this).get(RecordViewModel.class);
        recordViewModel.getRecords().observe(this, new Observer<List<Record>>() {
            @Override
            public void onChanged(@Nullable List<Record> records) {
                for(int i=0; i<records.size(); i++)
                {
                    Record currentRecord = records.get(i);
                    recordsTextView.setText(recordsTextView.getText() + currentRecord.toString());
                }
                information = Information.getInstance();
                information.setRecordList(records);
                user = User.getInstance();
                user.setInformation(information);

                Log.e("userrrr", user==null ? "null" : "not null");

//        Information information = user.getInformation();
                Log.e("infoooo", information==null ? "null" : "not null");

                List<Record> recordList = information.getRecordList();
                Log.e("record_list", recordList==null ? "null" : "not null");

                recordsTextView.setText(recordList.toString());

            }
        });
        return rootView;
    }


    //override this method to rename the toolbar title
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Home");
        super.onActivityCreated(savedInstanceState);
    }


}
