package com.example.android.mywallet2.model.record;

import com.example.android.mywallet2.model.categories.Category;

import java.util.Date;

public abstract class Record {

    private double amount;
    String image;
    private String note;
    private Date date;

    public Record(double amount, String image, String note, Date date) {
        this.amount = amount;
        this.image = image;
        this.note = note;
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public String getImage() {
        return image;
    }

    public String getNote() {
        return note;
    }

    public Date getDate() {
        return date;
    }





}
