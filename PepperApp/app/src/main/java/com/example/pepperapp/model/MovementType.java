package com.example.pepperapp.model;

public enum MovementType {
    HIP("Hip Movements"),NECK("Neck Movements"),SHOULDER("Shoulder Movements"),
    WRIST("Wrist Movements"),ELBOW("Elbow Movements"),FIST("Fist Movements"),
    COMBINED("Combined Movements");

    private String mCategoryTitle;

    MovementType(String categoryTitle) {
        this.mCategoryTitle = categoryTitle;
    }

    public String getmCategoryTitle() {
        return mCategoryTitle;
    }

    public void setmCategoryTitle(String mCategoryTitle) {
        this.mCategoryTitle = mCategoryTitle;
    }
}
