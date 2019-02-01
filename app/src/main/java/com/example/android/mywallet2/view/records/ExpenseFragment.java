package com.example.android.mywallet2.view.records;

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
import android.widget.EditText;
import android.widget.Spinner;

import com.example.android.mywallet2.R;
import com.example.android.mywallet2.model.Category;
import com.example.android.mywallet2.model.record.ExpenseRecord;
import com.example.android.mywallet2.model.record.Record;
import com.example.android.mywallet2.viewmodel.CategoriesViewModel;
import com.example.android.mywallet2.viewmodel.RecordViewModel;

import java.util.Date;
import java.util.List;

public class ExpenseFragment extends Fragment {

    private Button btnSaveExpense;
    private EditText editTextAmount, editTextPayee, editTextDate, editTextTime, editTextNote;
    private Spinner spinnerExpenseCategory;

    private List<Category> categoriesList;
    private CategoriesViewModel viewModel;


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
        String payee = editTextPayee.getText().toString();
        String date = editTextDate.getText().toString();
        String time = editTextTime.getText().toString();
        String note = editTextNote.getText().toString();

        String selectedCategory = spinnerExpenseCategory.getSelectedItem().toString();
        Category category = new Category(null, selectedCategory);


        Record record = new ExpenseRecord(amount, null, note, new Date(date), payee, category);
        return record;
    }

    private void populateSpinner() {
        viewModel = ViewModelProviders.of(this).get(CategoriesViewModel.class);
        viewModel.getCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(@Nullable List<Category> categories) {
                categoriesList = categories;
                String[] items = new String[categoriesList.size()];

                for(int i = 0; i < items.length; i++){
                    items[i] = categoriesList.get(i).getType();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                        android.R.layout.simple_spinner_dropdown_item, items);
                spinnerExpenseCategory.setAdapter(adapter);
            }
        });


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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
