package com.example.android.mywallet2.model;

public class User {
    private String firstName, lastName, email;
    private Information information;

    // TODO: list of Records here and its getter. (maybe also a method to add into the list, idk.)

    public User(String firstName, String lastName, String email, Information information) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.information = information;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }

}
