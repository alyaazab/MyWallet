package com.example.android.mywallet2.model.record;

import com.example.android.mywallet2.model.categories.Category;

import java.util.Date;

public class ExpenseRecord extends Record {

    private String payee;
    private Category category;

    public ExpenseRecord(double amount, String image, String note, Date date, String payee, Category category){
        super(amount, image, note, date);
        this.payee = payee;
        this.category = category;
    }
}
