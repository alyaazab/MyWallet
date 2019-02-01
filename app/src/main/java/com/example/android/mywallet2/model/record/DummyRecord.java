package com.example.android.mywallet2.model.record;

public class DummyRecord {

    private String category;

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    private double amount;

    public DummyRecord(String category, double amount) {
        this.category = category;
        this.amount = amount;
    }


}
