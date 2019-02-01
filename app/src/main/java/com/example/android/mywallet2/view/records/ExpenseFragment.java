package com.example.android.mywallet2.view.records;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.android.mywallet2.R;
import com.example.android.mywallet2.model.categories.Category;
import com.example.android.mywallet2.model.categories.Food;
import com.example.android.mywallet2.model.categories.Groceries;
import com.example.android.mywallet2.model.categories.Health;
import com.example.android.mywallet2.model.categories.Pets;
import com.example.android.mywallet2.model.categories.Shopping;
import com.example.android.mywallet2.model.categories.Transportation;
import com.example.android.mywallet2.model.categories.Utilities;
import com.example.android.mywallet2.model.categories.Vehicle;
import com.example.android.mywallet2.model.record.ExpenseRecord;
import com.example.android.mywallet2.model.record.Record;
import com.example.android.mywallet2.viewmodel.RecordViewModel;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ExpenseFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ExpenseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExpenseFragment extends Fragment {

    private Button btnSaveExpense;
    private EditText editTextAmount, editTextPayee, editTextDate, editTextTime, editTextNote;
    private Spinner spinnerExpenseCategory;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ExpenseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExpenseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExpenseFragment newInstance(String param1, String param2) {
        ExpenseFragment fragment = new ExpenseFragment();
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
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_expense, container, false);

        editTextAmount = rootView.findViewById(R.id.editTextExpenseAmount);
        spinnerExpenseCategory = rootView.findViewById(R.id.spinnerExpenseCategory);
        editTextPayee = rootView.findViewById(R.id.editTextExpensePayee);
        editTextDate = rootView.findViewById(R.id.editTextExpenseDate);
        editTextTime = rootView.findViewById(R.id.editTextExpenseTime);
        editTextNote = rootView.findViewById(R.id.editTextExpenseNote);
        btnSaveExpense = rootView.findViewById(R.id.buttonSaveExpense);


        populateSpinner();

        btnSaveExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Record record = createNewRecord();
                RecordViewModel recordViewModel = new RecordViewModel();
                recordViewModel.addNewRecord(record);

            }
        });



        return rootView;
    }

    private Record createNewRecord(){
        double amount = Double.valueOf(editTextAmount.getText().toString());
        String selectedCategory = spinnerExpenseCategory.getSelectedItem().toString();
        Category category;

        switch(selectedCategory){
            case "Food":
                category = new Food();
                break;
            case "Groceries":
                category = new Groceries();
                break;
            case "Health":
                category = new Health();
                break;
            case "Pets":
                category = new Pets();
                break;
            case "Shopping":
                category = new Shopping();
                break;
            case "Transportation":
                category = new Transportation();
                break;
            case "Utilities":
                category = new Utilities();
                break;
            case "Vehicle":
                category = new Vehicle();
                break;
            default:
                category = null;
        }

        String payee = editTextPayee.getText().toString();
        String date = editTextDate.getText().toString();
        String time = editTextTime.getText().toString();
        String note = editTextNote.getText().toString();

        Record record = new ExpenseRecord(amount, null, note, new Date(date), payee, category);
        return record;
    }

    private void populateSpinner() {
        String[] items = new String[]{"Food", "Groceries", "Health", "Pets", "Shopping",
                "Transportation", "Utilities", "Vehicle"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(),
                android.R.layout.simple_spinner_dropdown_item, items);
        spinnerExpenseCategory.setAdapter(adapter);
    }


    // TODO: Rename method, update argument and hook method into UI event
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
