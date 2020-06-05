package com.example.pepperapp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Movement {

    private int mMovementId;
    private String mMovementName;
    private MovementType mMovementType;
    private String mMovementDescription;
    private List<HashMap<JointType, Float>> mMove;

    public Movement(int mMovementId, String mMovementName, MovementType mMovementType, String mMovementDescription) {
        this.mMovementId = mMovementId;
        this.mMovementName = mMovementName;
        this.mMovementType = mMovementType;
        this.mMovementDescription = mMovementDescription;
        this.mMove = new ArrayList<>();
    }

    public String getmMovementName() {
        return mMovementName;
    }

    public void setmMovementName(String mMovementName) {
        this.mMovementName = mMovementName;
    }

    public MovementType getmMovementType() {
        return mMovementType;
    }

    public void setmMovementType(MovementType mMovementType) {
        this.mMovementType = mMovementType;
    }

    public String getmMovementDescription() {
        return mMovementDescription;
    }


    public void setmMovementDescription(String mMovementDescription) {
        this.mMovementDescription = mMovementDescription;
    }

    public int getmMovementId() {
        return mMovementId;
    }

    public void setmMovementId(int mMovementId) {
        this.mMovementId = mMovementId;
    }

}
