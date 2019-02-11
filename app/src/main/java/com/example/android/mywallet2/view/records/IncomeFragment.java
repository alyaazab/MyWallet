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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class IncomeFragment extends Fragment {


    private Button btnSaveIncome;
    private EditText editTextAmount, editTextNote, editTextSourceOfPayment;
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

        //display current date on date button
        String dateString = String.format("%02d/%02d/%04d", day, month+1, year);
        btnIncomeDate.setText(dateString);

        //get current time
        long milliseconds = calendar.getTimeInMillis();
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        //display current time on time button
        String timeString = String.format("%02d:%02d", hour, minute);
        btnIncomeTime.setText(timeString);

        //create a Date object using the current date
        date = new Date(milliseconds, second, minute, hour, day, month, year);


        btnIncomeTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        //display selected time on button
                        String str = String.format("%02d:%02d", selectedHour, selectedMinute);
                        btnIncomeTime.setText(str);

                        //change the "time" values in our date object to the selected time
                        date.setHour(selectedHour);
                        date.setMinute(selectedMinute);
                        date.setSecond(0);


                        try {
                            //create a SimpleDateFormat object to specify which date format we're using
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

                            //set the current time zone as the device's timezone
                            dateFormat.setTimeZone(TimeZone.getDefault());

                            //form a string using our date&time variables in the same format
                            // as our SimpleDateFormat object (dd/MM/yyyy HH:mm:ss)
                            String selectedDate = date.getDay() + "/" + date.getMonth()+1 + "/" +
                                    date.getYear() + " " + date.getHour() + ":" + date.getMinute()
                                    + ":" + date.getSecond();


                            //getTime() gives us the time elapsed from 1970 until the selectedDate
                            long milliseconds = dateFormat.parse(selectedDate).getTime();

                            //set the milliseconds (timestamp) in our Date object
                            date.setMilliseconds(milliseconds);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, hour, minute, false);
                timePickerDialog.show();
            }
        });

        btnIncomeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {

                        //display the selected date on the date button
                        btnIncomeDate.setText(selectedDay + "/" + (selectedMonth+1) + "/" + selectedYear);

                        //set the year, month and day of our Date object as the selected year, month and day
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
