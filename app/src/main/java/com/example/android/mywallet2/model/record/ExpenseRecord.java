package com.example.android.mywallet2.model.record;

import com.example.android.mywallet2.model.Category;
import com.example.android.mywallet2.model.Date;

public class ExpenseRecord extends Record {

    private String payee;
    private Category category;

    public ExpenseRecord(double amount, String image, String note, Date date, String payee, Category category){
        super(amount, image, note, date);
        this.payee = payee;
        this.category = category;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
