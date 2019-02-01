package com.example.android.mywallet2.model;

import com.example.android.mywallet2.model.period.Period;

public class Budget {
    private String name;
    private double amount;
    private Category category;
    private Period timePeriod;
    private boolean notificationBudgetOverspent;
    //private boolean notificationBudgetRisk;


    public Budget(String name, double amount, Category category, Period timePeriod, boolean notificationBudgetOverspent) {
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.timePeriod = timePeriod;
        this.notificationBudgetOverspent = notificationBudgetOverspent;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public Category getCategory() {
        return category;
    }

    public Period getTimePeriod() {
        return timePeriod;
    }

    public boolean isNotificationBudgetOverspent() {
        return notificationBudgetOverspent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setTimePeriod(Period timePeriod) {
        this.timePeriod = timePeriod;
    }

    public void setNotificationBudgetOverspent(boolean notificationBudgetOverspent) {
        this.notificationBudgetOverspent = notificationBudgetOverspent;
    }
}
