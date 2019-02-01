package com.example.android.mywallet2.model;

public class Category {

    String image;
    String type;

    public Category(String image, String type) {
        this.image = image;
        this.type = type;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public String getType() {
        return type;
    }
}
