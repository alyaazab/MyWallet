package com.example.android.mywallet2.model;

import android.graphics.Color;

import java.util.Date;

public class Goals {

    private String name;
    private double targetAmount;
    private double amountSaved;
    private Date desiredDate;
    private String note;
    private String icon;
    private Color goalColor;
    private double percentageSaved;
    //calculate the minimum amount needed to be paid per week to reach this goal
    private double minimumAmountPerWeek;
}
