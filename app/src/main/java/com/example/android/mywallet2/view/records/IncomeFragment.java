package com.example.android.mywallet2.view.records;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.example.android.mywallet2.R;
import com.example.android.mywallet2.model.Category;
import com.example.android.mywallet2.model.Date;
import com.example.android.mywallet2.model.record.ExpenseRecord;
import com.example.android.mywallet2.model.record.IncomeRecord;
import com.example.android.mywallet2.model.record.Record;
import com.example.android.mywallet2.viewmodel.CategoriesViewModel;
import com.example.android.mywallet2.viewmodel.RecordViewModel;

import java.util.Calendar;
import java.util.List;

public class IncomeFragment extends Fragment {


    private Button btnSaveIncome;
    private EditText editTextAmount, editTextDate, editTextTime, editTextNote,
            editTextSourceOfPayment;
    private Button btnIncomeDate;
    private Button btnIncomeTime;

    private Date date;


    private Calendar calendar;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public IncomeFragment() {
        // Required empty public constructor
    }

    public static IncomeFragment newInstance(String param1, String param2) {
        IncomeFragment fragment = new IncomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_income, container, false);
        findViews(rootView);
        getDate();

        btnSaveIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Record record = createNewRecord();
                RecordViewModel recordViewModel = new RecordViewModel();
                recordViewModel.addNewRecord(record);
            }
        });


        return rootView;
    }


    private void getDate() {
        //get current date
        calendar = Calendar.getInstance();
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int month = calendar.get(Calendar.MONTH);
        final int year = calendar.get(Calendar.YEAR);

        btnIncomeDate.setText(day + "/" + (month+1) + "/" + year);

        //get current time
        java.util.Date time = new java.util.Date();
        long second = time.getTime() / 1000;
        long hours = second / 3600;
        long minute = (time.getTime() - hours*3600000) / 60000;
        long hour = hours - 430318;
        final int intSecond = (int) second;
        final int intHour = (int) hour;
        final int intMinute = (int) minute;

        //set date as current date and time
        date = new Date(intSecond, intMinute, intHour, day, month, year);

        //display current time
        String str = String.format("%02d:%02d", hour, minute);
        btnIncomeTime.setText(str);


        btnIncomeTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String str = String.format("%02d:%02d", selectedHour, selectedMinute);
                        btnIncomeTime.setText(str);
                        date.setHour(selectedHour);
                        date.setMinute(selectedMinute);
                    }
                }, intHour, intMinute, true);
                timePickerDialog.show();
            }
        });

        btnIncomeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                        btnIncomeDate.setText(selectedDay + "/" + (selectedMonth+1) + "/" + selectedYear);
                        date.setYear(selectedYear);
                        date.setMonth(selectedMonth);
                        date.setDay(selectedDay);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

    }

    private void findViews(View rootView) {
        btnSaveIncome = rootView.findViewById(R.id.buttonSaveIncome);
        btnIncomeDate = rootView.findViewById(R.id.buttonIncomeDate);
        btnIncomeTime = rootView.findViewById(R.id.buttonIncomeTime);
        editTextNote = rootView.findViewById(R.id.editTextIncomeNote);
        editTextAmount = rootView.findViewById(R.id.editTextIncomeAmount);
        editTextSourceOfPayment = rootView.findViewById(R.id.editTextIncomeSource);
    }


    private Record createNewRecord(){
        double amount = Double.valueOf(editTextAmount.getText().toString());
        String note = editTextNote.getText().toString();
        String source = editTextSourceOfPayment.getText().toString();

        Record record = new IncomeRecord(amount, null, note, date, source);
        return record;
    }



    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
