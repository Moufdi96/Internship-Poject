package com.example.pepperapp.model;

public class Movement {
    private int mMovementId;
    private String mMovementName;
    private MovementType mMovementType;
    private String mMovementDescription;

    public Movement(int mMovementId,String mMovementName,MovementType mMovementType,String mMovementDescription){
        this.mMovementId = mMovementId;
        this.mMovementName = mMovementName;
        this.mMovementType = mMovementType;
        this.mMovementDescription = mMovementDescription;
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
