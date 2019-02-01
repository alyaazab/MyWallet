package com.example.android.mywallet2.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.android.mywallet2.R;
import com.example.android.mywallet2.model.record.Record;
import com.example.android.mywallet2.viewmodel.RecordViewModel;

public class RecordActivity extends AppCompatActivity {

    private EditText editTextAmount;
    private EditText editTextCategory;
    private RecordViewModel recordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
    }

    public void saveNewRecord(View view) {

        editTextAmount = findViewById(R.id.editTextAmount);
        editTextCategory = findViewById(R.id.editTextCategory);

        double amount = Double.valueOf(editTextAmount.getText().toString());
        String category = editTextCategory.getText().toString();

        recordViewModel = new RecordViewModel();
        recordViewModel.addNewRecord(amount, category);



    }
}
