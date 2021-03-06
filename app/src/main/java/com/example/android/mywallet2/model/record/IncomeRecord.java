package com.example.android.mywallet2.model.record;

import com.example.android.mywallet2.model.Date;

public class IncomeRecord extends Record {

    private String sourceOfPayment;

    public IncomeRecord(){

    }

    public IncomeRecord(double amount, String image, String note, Date date, String sourceOfPayment){
        super(amount, image, note, date);
        this.sourceOfPayment = sourceOfPayment;
    }

    public String getSourceOfPayment() {
        return sourceOfPayment;
    }

    public void setSourceOfPayment(String sourceOfPayment) {
        this.sourceOfPayment = sourceOfPayment;
    }
}
