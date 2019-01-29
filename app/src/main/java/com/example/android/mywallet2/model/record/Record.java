package com.example.android.mywallet2.model.record;

import java.util.Date;

public abstract class Record {

    public double amount;
    String image;
    public String note;
    public Date date;

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
